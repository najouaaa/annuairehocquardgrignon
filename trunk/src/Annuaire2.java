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
	 * removeRelais v�rifie que le relais voulant �tre supprim� est dans la table associative,
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
	 * On parcours la table associative grace � une boucle for et d'une particularit� des tables associatives
	 * on affiche tout les noms des relais pr�sent dans la table associative toutRelais
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
	 * @return retourne le relais le plus proche d'une position donn�e assurant un service demand�.
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
	 * On initialise la distance � une valeure tr�s grande
	 * on parcours la table associative grace � une boucle for associ� � une collection (avec keySet)
	 * si le relais parcouru est � plus petite distance que la distance temporaire, et qu'il comprend le service en param�tre,
	 * alors le relais parcouru est mis dans un relais temporaire, et la distance est remplac� par celle du relais au point en param�tre
	 * on r�it�re ce proc�d� pour tout les relais,
	 * puis on renvoie le relais qui se trouve dans le relais temporaire
	 * @param nomService String repr�sentant le nom du service demand�
	 * @param minute int repr�sentant l'heure � laquelle nous voulons savoir quand le service est assur�
	 * @param x double repr�sentant la position x demand�
	 * @param y double repr�sentant la position y demand�
	 * @return retourne le relais le plus proche d'une position donn�e assurant le service demand� � une heure donn�e.
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
	 * si les deux annuaires n'ont pas la meme taille, on renvoie false,
	 * sinon si les deux relais n'ont pas les memes valeures dans leurs collections, alors on revnoie false,
	 * si les deux premi�res conditions ne sont pas resp�ct�es, alors on renvoie true
	 * @param A2 Annuaire2 repr�sentant le second annuaire compar� (le premier �tant l'occurence plac� en this)
	 * @return renvoie si les deux annuaires sont �gaux ou non
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
	 * on parcours la table associative des relais par une boucle for et grace � .values)
	 * si il y a un des relais qui porte le nom plac� en param�tre, on renvoie true,
	 * sinon, on renvoie false
	 * @param nomRelais String repr�sentant le nom du relais cherch� dans l'annuaire2
	 * @return renvoie si le relais portant le nom plac� en param�tre est pr�sent dans la table associative this
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
	 * si on la table associative comporte le relais portant le nom plac� en param�tre, on renvoie le relais qui a comme cl� son nom
	 * sinon, on retourne null
	 * @param nomRelaisArg String repr�sentant le nom du relais voulant �tre renvoy�
	 * @return est retourn� le relais portant le nom plac� en param�tre pr�sent dans la table associative
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
	 * on parcours la table associative de relais par une boucle for et grace � keySet.
	 * et on appelle la m�thode afficherServices pour tout les relais
	 */
	public void afficherTout(){
		for(String key : toutRelais.keySet()){
			toutRelais.get(key).afficherServices();
		}
	}
	
	/**
	 * clear permet de supprimer tout les relais de l'annuaire2 plac� en this
	 * on utilise simplement la m�thode clear pour la table associative de relais
	 */
	public void clear(){
		this.toutRelais.clear();
	}
}
