import java.util.*;
/**
 * Relais est une classe représentant un relais
 * On considère qu'un relais est caractérisé par ses positions, ainsi que son nom
 * Le relais comprend aussi des services, interprété par un HashSet
 * @author Hocquard Grignon
 * 
 */
public class Relais {
	double x, y;
	String nomRelais;
	Collection<Service> servicesPresents = new HashSet<Service>();
	
	public Relais(){};
	/**
	 * Le constructeur permet de créer un relais et d'initialisé le nom ainsi que sa position grâce aux paramètres
	 * On fait des controles de saisie pour la positions (si x ou y < 0)
	 */
	public Relais(String nomRelais, double x, double y){
		if(x <0 | y< 0){
		this.nomRelais = nomRelais;
		this.x = x;
		this.y = y;
		}else{
			System.out.println("Erreurs dans les paramètres");
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
	//Méthode de classe///
	//////////////////////
	
	/**
	 * addService permet de rajouter un service au relais
	 * On créé d'abord un service avec son nom, puis le place dans la collection
	 * Le service créé porte le nom placé en paramètre
	 * @param servArg représente le nom du service créé
	 */
	public void addService(String servArg) {
			Service service = new Service(servArg);
			this.servicesPresents.add(service);
	}
	/**
	 * addService peut etre surchagé avec le debut et la fin d'une plage horaire,
	 * dans ce cas, il créé un service portant le nom placé en paramètre,
	 * puis rajoute une plage horaire disponible selon le debut et la fin placé en paramètre
	 * @param servArg représente le nom du service créé
	 * @param debut représente le debut de la disponibilite
	 * @param fin représente la fin de la disponibilite
	 */
	public void addService(String servArg, int debut, int fin){
		Service service = new Service(servArg);
		service.addDispo(debut, fin);
		this.servicesPresents.add(service);
	}
	
	/**
	 * removeService permet d'enlever un service du relais qui porte le nom placé en paramètre
	 * @param nomServiceArg représente le nom du service voulant etre supprimé
	 */
	public void removeService (String nomServiceArg){
		if(this.getService(nomServiceArg)){
			this.servicesPresents.remove(this.prendService(nomServiceArg));
		}else{
			System.out.println("Service introuvé");
		}
	}
	
	/**
	 * distanceTo renvoie la distance qui sépare le relais (this) du point placé en paramètre
	 * retourne la racine carré de la diagonale formé par les x et y du point placé en paramètre, et de la position du relais
	 * @param x1 double représentant la position x du point
	 * @param y1 double représentant la position y du point
	 * @return représentant la distance (arrondis) séparant le relais du point placé en paramètre
	 */
	public int distanceTo(double x1, double y1){
		return (int)(Math.sqrt(Math.pow(this.getX()-x1, 2)+Math.pow(this.getY()-y1, 2)));
	}
	
	/**
	 * servicesIsEqual renvoie un boolean pour savoir si deux relais proposent les mêmes services
	 * on parcours les deux collections des servicesPresents dans les deux relais grace à des iterators, et deux boucles while,
	 * puis on vérifie que la collection 1 comprend le nom du service de la collection 2,
	 * si ce n'est pas le cas, alors on retourne false,
	 * sinon, si tout les nom de service de l'un des relais sont présent dans le second, alors on renvoie true
	 * @param r2 représente le second relais comparé
	 * @return représentant si les deux relais proposent les mêmes services
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
	 * getService permet de savoir si un service portant le nom placé en paramètre existe dans le relais
	 * on parcours les services présents avec un iterator,
	 * si le service itéré a le meme nom que le paramètre, alors on renvoie true,
	 * sinon, si pour tout les services itérés, on n'a aucun service comportant le nom en paramètre,
	 * alors on revoie false
	 * @param servArg String représentant le nom du service recherché
	 * @return True si le service portant le nom placé en paramètre existe dans le relais
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
	 * prendService renvoie le service qui a le même nom que le nom placé en paramètre
	 * on parcours les services présents avec un iterator, et une boucle while,
	 * si le service itéré est un
	 * @param servArg String représentant le nom du service voulant être retourné
	 * @return service avec le nom placé en paramètre
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
	 * afficherServices permet d'afficher tout les services présent dans le relais, avec leurs disponibilités
	 * parcours les services presents grace à un iterator et une boucle while,
	 * on affiche tout les noms de service itérés
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
