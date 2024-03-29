import java.util.*;
/**
 * Relais est une classe repr�sentant un relais
 * On consid�re qu'un relais est caract�ris� par ses positions, ainsi que son nom
 * Le relais comprend aussi des services, interpr�t� par un HashSet
 * @author Hocquard Grignon
 * 
 */
public class Relais {
	double x, y;
	String nomRelais;
	Collection<Service> servicesPresents = new HashSet<Service>();
	
	public Relais(){};
	/**
	 * Le constructeur permet de cr�er un relais et d'initialis� le nom ainsi que sa position gr�ce aux param�tres
	 * On fait des controles de saisie pour la positions (si x ou y < 0)
	 */
	public Relais(String nomRelais, double x, double y){
		if(x <0 | y< 0){
		this.nomRelais = nomRelais;
		this.x = x;
		this.y = y;
		}else{
			System.out.println("Erreurs dans les param�tres");
		}
	}
	
	/**
	 * getter classique sur x
	 * @return x de this
	 */
	public double getX(){
		return this.x;
	}
	/**
	 * getter classique sur y
	 * @return y de this
	 */
	public double getY(){
		return this.y;
	}
	/**
	 * setter classique sur x
	 */
	public void setX(double x){
		this.x = x;
	}
	/**
	 * setter classique sur y
	 */
	public void setY(double y){
		this.y = y;
	}
	/**
	 * getter classique sur nomRelais
	 * @return nomRelais de this
	 */
	public String getNomRelais(){
		return this.nomRelais;
	}
	/**
	 * setter classique sur nomRelais
	 */
	public void setNomRelais(String nomRelais){
		this.nomRelais = nomRelais;
	}
	
	//////////////////////
	//M�thode de classe///
	//////////////////////
	
	/**
	 * addService permet de rajouter un service au relais
	 * On cr�� d'abord un service avec son nom, puis le place dans la collection
	 * Le service cr�� porte le nom plac� en param�tre
	 * @param servArg repr�sente le nom du service cr��
	 */
	public void addService(String servArg) {
			Service service = new Service(servArg);
			this.servicesPresents.add(service);
	}
	/**
	 * addService peut etre surchag� avec le debut et la fin d'une plage horaire,
	 * dans ce cas, il cr�� un service portant le nom plac� en param�tre,
	 * puis rajoute une plage horaire disponible selon le debut et la fin plac� en param�tre
	 * @param servArg repr�sente le nom du service cr��
	 * @param debut repr�sente le debut de la disponibilite
	 * @param fin repr�sente la fin de la disponibilite
	 */
	public void addService(String servArg, int debut, int fin){
		Service service = new Service(servArg);
		service.addDispo(debut, fin);
		this.servicesPresents.add(service);
	}
	
	/**
	 * removeService permet d'enlever un service du relais qui porte le nom plac� en param�tre
	 * @param nomServiceArg repr�sente le nom du service voulant etre supprim�
	 */
	public void removeService (String nomServiceArg){
		if(this.getService(nomServiceArg)){
			this.servicesPresents.remove(this.prendService(nomServiceArg));
		}else{
			System.out.println("Service introuv�");
		}
	}
	
	/**
	 * distanceTo renvoie la distance qui s�pare le relais (this) du point plac� en param�tre
	 * retourne la racine carr� de la diagonale form� par les x et y du point plac� en param�tre, et de la position du relais
	 * @param x1 double repr�sentant la position x du point
	 * @param y1 double repr�sentant la position y du point
	 * @return repr�sentant la distance (arrondis) s�parant le relais du point plac� en param�tre
	 */
	public int distanceTo(double x1, double y1){
		return (int)(Math.sqrt(Math.pow(this.getX()-x1, 2)+Math.pow(this.getY()-y1, 2)));
	}
	
	/**
	 * servicesIsEqual renvoie un boolean pour savoir si deux relais proposent les m�mes services
	 * on parcours les deux collections des servicesPresents dans les deux relais grace � des iterators, et deux boucles while,
	 * puis on v�rifie que la collection 1 comprend le nom du service de la collection 2,
	 * si ce n'est pas le cas, alors on retourne false,
	 * sinon, si tout les nom de service de l'un des relais sont pr�sent dans le second, alors on renvoie true
	 * @param r2 repr�sente le second relais compar�
	 * @return repr�sentant si les deux relais proposent les m�mes services
	 */
	public boolean servicesIsEqual(Relais r2){
		Iterator<Service> it = this.servicesPresents.iterator();
		while(it.hasNext()){
			Service service = it.next();
			if(r2.getService(service.nomService) ==false){
				return false;
			}
		}
		Iterator<Service> it2 = r2.servicesPresents.iterator();
		while(it2.hasNext()){
			Service service = it2.next();
			if(this.getService(service.nomService) ==false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * getService permet de savoir si un service portant le nom plac� en param�tre existe dans le relais
	 * on parcours les services pr�sents avec un iterator,
	 * si le service it�r� a le meme nom que le param�tre, alors on renvoie true,
	 * sinon, si pour tout les services it�r�s, on n'a aucun service comportant le nom en param�tre,
	 * alors on revoie false
	 * @param servArg String repr�sentant le nom du service recherch�
	 * @return True si le service portant le nom plac� en param�tre existe dans le relais
	 */
	public boolean getService(String servArg){
		Iterator<Service> it = servicesPresents.iterator();
		while(it.hasNext()){
			Service service = it.next();
			if(service.getNomService()==servArg){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * prendService renvoie le service qui a le m�me nom que le nom plac� en param�tre
	 * on parcours les services pr�sents avec un iterator, et une boucle while,
	 * si le service it�r� est un
	 * @param servArg String repr�sentant le nom du service voulant �tre retourn�
	 * @return service avec le nom plac� en param�tre
	 */
	public Service prendService(String servArg){
		if (this.getService(servArg)){
			Iterator<Service> it = servicesPresents.iterator();
			while(it.hasNext()){
				Service service = it.next();
				if(service.getNomService()==servArg){
					return service;
				}
			}
		}else{
			System.out.println("Service inexistant!!!");
		}
		return null;
	}
	
	/**
	 * afficherServices permet d'afficher tout les services pr�sent dans le relais, avec leurs disponibilit�s
	 * parcours les services presents grace � un iterator et une boucle while,
	 * on affiche tout les noms de service it�r�s
	 */
	public void afficherServices(){
		Iterator<Service> it = servicesPresents.iterator();
		System.out.println("Le relais : "+this.getNomRelais()+", a les services : ");
		while(it.hasNext()){
			Service service = it.next();
			System.out.println("-"+service.getNomService());
			service.afficherDispo(0);
		}
		System.out.println("\n-----------------\n");
	}
}
