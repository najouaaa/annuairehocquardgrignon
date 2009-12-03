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
	 */
	public Relais(String nomRelais, double x, double y){
		this.nomRelais = nomRelais;
		this.x = x;
		this.y = y;
	}
	
	//Setters and Getters, classiques
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public String getNomRelais(){
		return this.nomRelais;
	}
	public void setNomRelais(String nomRelais){
		this.nomRelais = nomRelais;
	}
	
	//////////////////////
	//M�thode de classe///
	//////////////////////
	
	/**
	 * addService permet de rajouter un service au relais
	 * Le service cr�� porte le nom plac� en param�tre
	 */
	public void addService(String servArg) {
			Service service = new Service(servArg);
			this.servicesPresents.add(service);
	}
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
	 * @param x1 double repr�sentant la position x du point
	 * @param y1 double repr�sentant la position y du point
	 * @return int repr�sentant la distance (arrondis) s�parant le relais du point plac� en param�tre
	 */
	public int distanceTo(double x1, double y1){
		return (int)(Math.sqrt(Math.pow(this.getX()-x1, 2)+Math.pow(this.getY()-y1, 2)));
	}
	
	/**
	 * servicesIsEqual renvoie un boolean pour savoir si deux relais proposent les m�mes services
	 * @param r2 repr�sente le second relais compar�
	 * @return boolean repr�sentant si les deux relais proposent les m�mes services
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
	 * @param servArg String repr�sentant le nom du service recherch�
	 * @return boolean : True si le service portant le nom plac� en param�tre existe dans le relais
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
	 * @param servArg String repr�sentant le nom du service voulant �tre retourn�
	 * @return service avec le nom plac� en param�tre
	 */
	public Service prendService(String servArg){
		Iterator<Service> it = servicesPresents.iterator();
			while(it.hasNext()){
				Service service = it.next();
				if(service.getNomService()==servArg){
					return service;
				}
			}
		System.out.println("Service inexistant !!!!");
		return null;
	}
	
	/**
	 * afficherServices permet d'afficher tout les services pr�sent dans le relais, avec leurs disponibilit�s
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
