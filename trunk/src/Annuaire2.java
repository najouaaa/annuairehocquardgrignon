import java.util.HashMap;
import java.util.Iterator;


public class Annuaire2 {
	HashMap<String, Relais> toutRelais = new HashMap<String, Relais>();
	//Constructeur
	public Annuaire2(){
		
	}
	//Ajout d'un relais
	public void addRelais(String nomRelais, double x, double y){
		Relais rTemp = new Relais(nomRelais, x, y);
		this.toutRelais.put(nomRelais, rTemp);
	}
	//Supprimer un relais
	public void removeRelais(String nomRelais){
		this.toutRelais.remove(nomRelais);
	}
	
	//Affichage des coordonnées des relais ayant le nom du service en paramètre
	public void afficheRelais (String nomService) {
		//à refaire
		/*Iterator<Relais> it = this.toutRelais.iterator();
		System.out.println("Les relais avec le service "+nomService+" sont :");
		while(it.hasNext()){
			Relais rTemp = it.next();
			if(rTemp.getService(nomService)){
				System.out.println(rTemp.getNomRelais()+": X="+rTemp.getX()+", Y="+rTemp.getY());
			}
		}
		System.out.println("");*/

	}
	
	// chercher le relais le plus proche proposant un service donné 
	public Relais chercheRelais(String nomService, int minute, double x, double y){
		//A refaire
		/*Iterator<Relais> it = this.toutRelais.iterator();
		double distance = 999999999;
		Relais rTemp = it.next();
		Relais rRes = new Relais();
		while(it.hasNext()){
			if( (rTemp.getService(nomService)) && (rTemp.distanceTo(x, y)<distance ) && rTemp.prendService(nomService).isDispo(minute)){
				rRes = rTemp;
			}
			rTemp = it.next();
		}
		return rRes;*/
	}
	
	
	//Fonction qui retourne vrai si deux annuaire ont les memes relais
	public boolean isEqual1(Annuaire2 A2){
		if(this.toutRelais.size() != A2.toutRelais.size()){
			return false;
		}else if(this.toutRelais.values() == A2.toutRelais.values()){
			return true;
		}else{
			return false;
		}
	}
	
	//Savoir si l'annuaire a le relais passé en paramètre
	public boolean getRelais(String nomRelais){
		//A refaire
		
		/*Iterator<Relais> it = toutRelais.iterator();
		while(it.hasNext()){
			Relais relaisTemp = it.next();
			if(relaisTemp.getNomRelais() == nomRelais){
				return true;
			}
		}
		return false;*/
	}
	//renvoie le Relais qui a le nom de celui-ci en argument
	public Relais prendRelais(String nomRelaisArg){
		//A refaire
		/*Iterator<Relais> it = toutRelais.iterator();
			while(it.hasNext()){
				Relais relais = it.next();
				if(relais.getNomRelais()==nomRelaisArg){
					return relais;
				}
			}
		System.out.println("Non prise du Relais !!!!");
		return null;*/
	}
	
	
	//affichage des relais avec leurs services
	public void afficherTout(){
		//A refaire
		/*Iterator<Relais> it = toutRelais.iterator();
		while(it.hasNext()){
			Relais relais = it.next();
			relais.afficherServices();
		}*/
	}
}
