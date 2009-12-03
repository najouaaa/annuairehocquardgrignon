import java.util.HashMap;

/**
 * Annuaire2 permet de mod�liser une map de Relais.
 * Les keys sont sous forme de String, ils repr�sente les noms des Relais.
 * On consid�re que les noms sont repr�sentatif d'un relais.
 * {@link #toutRelais} toutRelais est une HashMap de keys sous forme de String et d'une collections de Relais associ�.
 * @author Hocquard Grignon
 * @since 2009
 * @version 1.0
 */
public class Annuaire2 {
	HashMap<String, Relais> toutRelais = new HashMap<String, Relais>();
	/**
	 * Le constructeur est vide, il sert juste � cr�er une occurence d'annuaire2
	 * @param null
	 */
	public Annuaire2(){
		
	}
	/**
	 * addRelais cr�er un relais avec les caract�ristiques n�cessaire en param�tre,
	 * puis le rajoute � la table associative de l'occurence Annuaire2
	 * @param nomRelais String repr�sentant le nom du relais, on consid�re qu'un relais est caract�ris� par sa position et son nom
	 * @param x double repr�sentant la position x du relais
	 * @param y double repr�sentant la position y du relais
	 */
	public void addRelais(String nomRelais, double x, double y){
		Relais rTemp = new Relais(nomRelais, x, y);
		this.toutRelais.put(nomRelais, rTemp);
	}
	/**
	 * removeRelais v�rifie que le relais voulant �tre supprimer est dans la table associative,
	 * si c'est le cas, la m�thode le supprime de cette table.
	 * @param nomRelais String repr�sentant le nom du relais que nous voulont supprimer
	 */
	public void removeRelais(String nomRelais){
		if(this.getRelais(nomRelais)){
			this.toutRelais.remove(nomRelais);
		}
	}
	
	/**
	 * afficheRelais affiche dans la console tout les relais pr�sent dans la table associative avec le service demand�.
	 * @param nomService String repr�sentant le Service que nous voulons chercher dans la table associative
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
	 * chercheRelais renvoie le relais le plus proche d'une position donn�e assurant un service demand�.
	 * @param nomService String repr�sentant le service demand�.
	 * @param x double repr�sentant la position x demand�
	 * @param y double repr�sentant la position y demand�
	 * @return relais : retourne le relais le plus proche d'une position donn�e assurant un service demand�.
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
	 * chercheRelais renvoie le relais le plus proche d'une position donn�e, assurant un service demand� � une heure donn�e.
	 * @param nomService String repr�sentant le nom du service demand�
	 * @param minute int repr�sentant l'heure � laquelle nous voulons savoir quand le service est assur�
	 * @param x double repr�sentant la position x demand�
	 * @param y double repr�sentant la position y demand�
	 * @return relais : retourne le relais le plus proche d'une position donn�e assurant le service demand� � une heure donn�e.
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
	 * isEqual1 renvoie un boolean disant si deux annuaires ont les m�mes relais (sachant qu'un relais est unique, alors les annuaires sont �gaux)
	 * Les annuaire propose alors les memes relais, les memes service, ainsi que les meme disponibilit�s de service.
	 * @param A2 Annuaire2 repr�sentant le second annuaire compar� (le premier �tant l'occurence plac� en this)
	 * @return boolean : renvoie si les deux annuaires sont �gaux ou non
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
	 * getRelais renvoie si l'annuaire2 en this contient le relais portant le nom plac� en param�tre
	 * @param nomRelais String repr�sentant le nom du relais cherch� dans l'annuaire2
	 * @return boolean : renvoie si le relais portant le nom plac� en param�tre est pr�sent dans la table associative this
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
	 * prendRelais renvoie le relais qui porte le nom plac� en param�tre
	 * @param nomRelaisArg String repr�sentant le nom du relais voulant �tre renvoy�
	 * @return relais : est renvoy� le relais portant le nom plac� en param�tre pr�sent dans la table associative
	 */
	public Relais prendRelais(String nomRelaisArg){
		if(getRelais(nomRelaisArg)){
			return toutRelais.get(nomRelaisArg);
		}else{
			return null;
		}
	}
	
	
	/**
	 * afficherTout sert � afficher en console toutes les disponibilit�s des services de la table associative.
	 */
	public void afficherTout(){
		for(String key : toutRelais.keySet()){
			toutRelais.get(key).afficherServices();
		}
	}
	
	/**
	 * clear permet de supprimer tout les relais de l'annuaire2 plac� en this
	 */
	public void clear(){
		this.toutRelais.clear();
	}
}
