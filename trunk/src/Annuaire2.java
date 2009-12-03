import java.util.HashMap;

/**
 * Annuaire2 permet de modéliser une map de Relais.
 * Les keys sont sous forme de String, ils représente les noms des Relais.
 * On considère que les noms sont représentatif d'un relais.
 * {@link #toutRelais} toutRelais est une HashMap de keys sous forme de String et d'une collections de Relais associé.
 * @author Hocquard Grignon
 * @since 2009
 * @version 1.0
 */
public class Annuaire2 {
	HashMap<String, Relais> toutRelais = new HashMap<String, Relais>();
	/**
	 * Le constructeur est vide, il sert juste à créer une occurence d'annuaire2
	 * @param null
	 */
	public Annuaire2(){
		
	}
	/**
	 * addRelais créer un relais avec les caractèristiques nécessaire en paramètre,
	 * puis le rajoute à la table associative de l'occurence Annuaire2
	 * @param nomRelais String représentant le nom du relais, on considère qu'un relais est caractérisé par sa position et son nom
	 * @param x double représentant la position x du relais
	 * @param y double représentant la position y du relais
	 */
	public void addRelais(String nomRelais, double x, double y){
		Relais rTemp = new Relais(nomRelais, x, y);
		this.toutRelais.put(nomRelais, rTemp);
	}
	/**
	 * removeRelais vérifie que le relais voulant être supprimer est dans la table associative,
	 * si c'est le cas, la méthode le supprime de cette table.
	 * @param nomRelais String représentant le nom du relais que nous voulont supprimer
	 */
	public void removeRelais(String nomRelais){
		if(this.getRelais(nomRelais)){
			this.toutRelais.remove(nomRelais);
		}
	}
	
	/**
	 * afficheRelais affiche dans la console tout les relais présent dans la table associative avec le service demandé.
	 * @param nomService String représentant le Service que nous voulons chercher dans la table associative
	 */
	public void afficheRelais (String nomService) {
		System.out.println("Les relais avec le service "+nomService+" sont :");
		for(String key : toutRelais.keySet()){
			if(toutRelais.get(key).getService(nomService)){
				System.out.println(toutRelais.get(key).getNomRelais()+": X="+toutRelais.get(key).getX()+", Y="+toutRelais.get(key).getY());
			}
		}
		System.out.println("");

	}

	/**
	 * chercheRelais renvoie le relais le plus proche d'une position donnée assurant un service demandé.
	 * @param nomService String représentant le service demandé.
	 * @param x double représentant la position x demandé
	 * @param y double représentant la position y demandé
	 * @return relais : retourne le relais le plus proche d'une position donnée assurant un service demandé.
	 */
	public Relais chercheRelais(String nomService, double x, double y){
		double distance = 999999999;
		Relais rRes = new Relais();
		for(String key : toutRelais.keySet()){
			if( (toutRelais.get(key).getService(nomService)) && (toutRelais.get(key).distanceTo(x, y)<distance )){
				rRes = toutRelais.get(key);
			}
		}
		return rRes;
	}
	
	/**
	 * chercheRelais renvoie le relais le plus proche d'une position donnée, assurant un service demandé à une heure donnée.
	 * @param nomService String représentant le nom du service demandé
	 * @param minute int représentant l'heure à laquelle nous voulons savoir quand le service est assuré
	 * @param x double représentant la position x demandé
	 * @param y double représentant la position y demandé
	 * @return relais : retourne le relais le plus proche d'une position donnée assurant le service demandé à une heure donnée.
	 */
	public Relais chercheRelais(String nomService, int minute, double x, double y){
		double distance = 999999999;
		Relais rRes = new Relais();
		for(String key : toutRelais.keySet()){
			if( (toutRelais.get(key).getService(nomService)) && (toutRelais.get(key).distanceTo(x, y)<distance ) && toutRelais.get(key).prendService(nomService).isDispo(minute)){
				rRes = toutRelais.get(key);
			}
		}
		return rRes;
	}
	
	
	/**
	 * isEqual1 renvoie un boolean disant si deux annuaires ont les mêmes relais (sachant qu'un relais est unique, alors les annuaires sont égaux)
	 * Les annuaire propose alors les memes relais, les memes service, ainsi que les meme disponibilités de service.
	 * @param A2 Annuaire2 représentant le second annuaire comparé (le premier étant l'occurence placé en this)
	 * @return boolean : renvoie si les deux annuaires sont égaux ou non
	 */
	public boolean isEqual1(Annuaire2 A2){
		if(this.toutRelais.size() != A2.toutRelais.size()){
			return false;
		}else if(this.toutRelais.values() == A2.toutRelais.values()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * getRelais renvoie si l'annuaire2 en this contient le relais portant le nom placé en paramètre
	 * @param nomRelais String représentant le nom du relais cherché dans l'annuaire2
	 * @return boolean : renvoie si le relais portant le nom placé en paramètre est présent dans la table associative this
	 */
	public boolean getRelais(String nomRelais){
		for(String key : toutRelais.keySet()){
			if(toutRelais.get(key).getNomRelais() == nomRelais){
				return true;
			}
		}
		return false;
	}
	/**
	 * prendRelais renvoie le relais qui porte le nom placé en paramètre
	 * @param nomRelaisArg String représentant le nom du relais voulant être renvoyé
	 * @return relais : est renvoyé le relais portant le nom placé en paramètre présent dans la table associative
	 */
	public Relais prendRelais(String nomRelaisArg){
		if(getRelais(nomRelaisArg)){
			return toutRelais.get(nomRelaisArg);
		}else{
			return null;
		}
	}
	
	
	/**
	 * afficherTout sert à afficher en console toutes les disponibilités des services de la table associative.
	 */
	public void afficherTout(){
		for(String key : toutRelais.keySet()){
			toutRelais.get(key).afficherServices();
		}
	}
	
	/**
	 * clear permet de supprimer tout les relais de l'annuaire2 placé en this
	 */
	public void clear(){
		this.toutRelais.clear();
	}
}
