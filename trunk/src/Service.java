import java.util.ArrayList;
/**
 * Service représente un service donnée, il est constitué d'une arraylist représentant sa disponibilité, et d'un nom de service le représentant.
 * @author Hocquard Grignon
 */
public class Service {
	ArrayList<Disponibilite> touteDisponibilite = new ArrayList<Disponibilite>();
	String nomService;

	/**
	 * Le constructeur prend en paramètre le nom du service qui doit etre créé.
	 * Nous initialisons les disponibilité à false
	 * @param serv String représentant le nom du service
	 */
	public Service(String serv){
		this.nomService = serv;
		createDispo(false);
	}
	
	
	//getters and setters
	public void setNomService(String nomArg){
		this.nomService = nomArg;
	}
	public String getNomService(){
		return this.nomService;
	}
	
	/**
	 * afficherDispo affiche dans la console les disponibilité d'un service
	 * @param i int représentant la minute du début de la disponibilité, attention, il y a de la récursivité qui fait que le i change.
	 */
	public void afficherDispo(int i){
		if(i<1440){
			Disponibilite dispoTemp = this.touteDisponibilite.get(i);
			while (!dispoTemp.isDispo() && i<1440){
				dispoTemp = this.touteDisponibilite.get(i);
				i++;		
			}
			if(i <1440){
				System.out.println("début : " + dispoTemp.getMinute());
				while(dispoTemp.isDispo() && i<1440){
					dispoTemp = this.touteDisponibilite.get(i);
					i++;
				}
				System.out.println("fin : " + (dispoTemp.getMinute()-1) );
				afficherDispo(i);
			}
		}
	}
	
	/**
	 * addDispo permet de mettre à true les disponibilité d'un service placé en paramètre
	 * @param minuteDebut int représentant la minute du début de p
	 * @param minuteFin
	 */
	public void addDispo(int minuteDebut, int minuteFin){
		if (minuteDebut>=0){
			for (int i=minuteDebut; i<=minuteFin && i<1440; i++){
				this.touteDisponibilite.get(i).setDispo(true);
			}
		}else{
			System.out.println("Mauvaises disponibilitees en parametre");
		}
	}
	/**
	 * 
	 * @param minuteDebut
	 * @param minuteFin
	 */
	public void deleteDispo(int minuteDebut, int minuteFin){
		if (minuteDebut>=0){
			for (int i=minuteDebut; i<=minuteFin && i<1440; i++){
				this.touteDisponibilite.get(i).setDispo(false);
			}
		}else{
			System.out.println("Mauvaises disponibilitees en parametre");
		}
	}
	
	// initialisation de la disponibilité d'un service 
	// temps exprimés en minutes sur une journée (24*60 = 1440 minutes)

	public void createDispo(boolean is){
		for(int i=0; i<1440; i++){
			Disponibilite dispo = new Disponibilite(i, is);
			this.touteDisponibilite.add(dispo);
		}
	}
	
	//retourne si oui ou non un service est disponible à un horaire donné
	public boolean isDispo(int minute){
		return this.touteDisponibilite.get(minute).isDispo();
	}
}
