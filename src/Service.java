import java.util.ArrayList;
/**
 * Service repr�sente un service donn�e, il est constitu� d'une arraylist repr�sentant sa disponibilit�, et d'un nom de service le repr�sentant.
 * @author Hocquard Grignon
 */
public class Service {
	ArrayList<Disponibilite> touteDisponibilite = new ArrayList<Disponibilite>();
	String nomService;

	/**
	 * Le constructeur prend en param�tre le nom du service qui doit etre cr��.
	 * Nous initialisons les disponibilit� � false
	 * @param serv String repr�sentant le nom du service
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
	 * afficherDispo affiche dans la console les disponibilit� d'un service
	 * @param i int repr�sentant la minute du d�but de la disponibilit�, attention, il y a de la r�cursivit� qui fait que le i change.
	 */
	public void afficherDispo(int i){
		if(i<1440){
			Disponibilite dispoTemp = this.touteDisponibilite.get(i);
			while (!dispoTemp.isDispo() && i<1440){
				dispoTemp = this.touteDisponibilite.get(i);
				i++;		
			}
			if(i <1440){
				System.out.println("d�but : " + dispoTemp.getMinute());
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
	 * addDispo permet de mettre � true les disponibilit� d'un service plac� en param�tre
	 * @param minuteDebut int repr�sentant la minute du d�but de p
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
	
	// initialisation de la disponibilit� d'un service 
	// temps exprim�s en minutes sur une journ�e (24*60 = 1440 minutes)

	public void createDispo(boolean is){
		for(int i=0; i<1440; i++){
			Disponibilite dispo = new Disponibilite(i, is);
			this.touteDisponibilite.add(dispo);
		}
	}
	
	//retourne si oui ou non un service est disponible � un horaire donn�
	public boolean isDispo(int minute){
		return this.touteDisponibilite.get(minute).isDispo();
	}
}
