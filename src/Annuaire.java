import java.util.*;

public class Annuaire {
	ArrayList<Relais> toutRelais = new ArrayList<Relais>();
	//Constructeur
	public Annuaire(){
		
	}
	//Ajout d'un relais
	public void addRelais(String nomRelais, double x, double y){
		Relais rTemp = new Relais(nomRelais, x, y);
		this.toutRelais.add(rTemp);
	}
	//Supprimer un relais
	public void removeRelais(String nomRelais){
		if(this.getRelais(nomRelais)){
			this.toutRelais.remove(this.prendRelais(nomRelais));
		}else{
			System.out.println("Relais introuvé");
		}
	}
	
	//Affichage des coordonnées des relais ayant le nom du service en paramètre
	public void afficheRelais (String nomService) {
		Iterator<Relais> it = this.toutRelais.iterator();
		System.out.println("Les relais avec le service "+nomService+" sont :");
		while(it.hasNext()){
			Relais rTemp = it.next();
			if(rTemp.getService(nomService)){
				System.out.println(rTemp.getNomRelais()+": X="+rTemp.getX()+", Y="+rTemp.getY());
			}
		}
		System.out.println("");

	}
	
	// chercher le relais le plus proche proposant un service donné 
	public Relais chercheRelais(String nomService, int minute, double x, double y){
		Iterator<Relais> it = this.toutRelais.iterator();
		double distance = 999999999;
		Relais rTemp = it.next();
		Relais rRes = new Relais();
		while(it.hasNext()){
			if( (rTemp.getService(nomService)) && (rTemp.distanceTo(x, y)<distance ) && rTemp.prendService(nomService).isDispo(minute)){
				rRes = rTemp;
			}
			rTemp = it.next();
		}
		return rRes;
	}
	
	
	//Fonction qui retourne vrai si deux annuaire ont les memes relais
	public boolean isEqual1(Annuaire A2){
		Iterator<Relais> it = this.toutRelais.iterator();
		while(it.hasNext()){
			Relais relais = it.next();
			if(A2.getRelais(relais.nomRelais)==false){
				return false;
			}
		}
		Iterator<Relais> it2= A2.toutRelais.iterator();
		//on vérifie que "this" contient A2, puis que A2 contient "this"
		while(it2.hasNext()){
			Relais relais = it2.next();
			if(this.getRelais(relais.nomRelais)==false){
				return false;
			}
		}
		return true;
	}
	
	//Savoir si l'annuaire a le relais passé en paramètre
	public boolean getRelais(String nomRelais){
		Iterator<Relais> it = toutRelais.iterator();
		while(it.hasNext()){
			Relais relaisTemp = it.next();
			if(relaisTemp.getNomRelais() == nomRelais){
				return true;
			}
		}
		return false;
	}
	//renvoie le Relais qui a le nom de celui-ci en argument
	public Relais prendRelais(String nomRelaisArg){
		Iterator<Relais> it = toutRelais.iterator();
			while(it.hasNext()){
				Relais relais = it.next();
				if(relais.getNomRelais()==nomRelaisArg){
					return relais;
				}
			}
		System.out.println("Non prise du Relais !!!!");
		return null;
	}
	
	
	//affichage des relais avec leurs services
	public void afficherTout(){
		Iterator<Relais> it = toutRelais.iterator();
		while(it.hasNext()){
			Relais relais = it.next();
			relais.afficherServices();
		}
	}
}
