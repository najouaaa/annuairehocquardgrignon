import java.util.*;
public class Relais {
	double x, y;
	String nomRelais;
	//HashSet >> Faut enlever un objet
	Collection<Service> servicesPresents = new HashSet<Service>();
	
	public Relais(){};
	//Constructeur surchargé
	public Relais(String nomRelais, double x, double y){
		this.nomRelais = nomRelais;
		this.x = x;
		this.y = y;
	}
	
	//Setters and Getters
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
	//Méthode de classe///
	//////////////////////
	
	//Ajouter un service du relais avec ou sans les horaires : surcharge de méthode
	public void addService(String servArg) {
			Service service = new Service(servArg);
			this.servicesPresents.add(service);
	}
	public void addService(String servArg, int debut, int fin){
		Service service = new Service(servArg);
		service.addDispo(debut, fin);
		this.servicesPresents.add(service);
	}
	
	//Enlever un service du relais 
	public void removeService (String nomServiceArg){
		if(this.getService(nomServiceArg)){
			this.servicesPresents.remove(this.prendService(nomServiceArg));
		}else{
			System.out.println("Service introuvé");
		}
	}
	
	//Retourner la distance entre un relais et un point
	public int distanceTo(double x1, double y1){
		return (int)(Math.sqrt(Math.pow(this.getX()-x1, 2)+Math.pow(this.getY()-y1, 2)));
	}
	
	//Retourner si deux relais ont les mêmes services 
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
	
	//Retourne si un service, avec un nom caractéristique servArg, est dans le relais ou non
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
	
	//renvoie le service qui a le nom de service en String
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
	
	// affichage de tous les services avec leurs disponibilités
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
