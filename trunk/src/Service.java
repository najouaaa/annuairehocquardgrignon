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
	 * Le service n'est pas disponible par défaut
	 * Nous initialisons les disponibilité du service à false.
	 * @param serv String représentant le nom du service
	 */
	public Service(String serv){
		this.nomService = serv;
		createDispo(false);
	}
	
	
	/**
	 * Setter nomService
	 */
	public void setNomService(String nomArg){
		this.nomService = nomArg;
	}
	/**
	 * getter nomService
	 * @return nomService de this
	 */
	public String getNomService(){
		return this.nomService;
	}
	
	/**
	 * afficherDispo affiche dans la console les disponibilité d'un service
	 * Le fonction doit être appellée avec 0 pour afficher la disponibilité de toute la journée
	 * On vérifie que le i ne dépasse pas la journée durant toute la fonction, si c'est le cas, soit on affiche la fin de la journée, soit on n'affiche rien.
	 * On parcours les disponibilités du service, et dès que l'on trouve une plage disponible, on affiche le premier i de la plage,
	 * puis on incrémente i jusqu'à trouver la fin de la plage horaire,
	 * on affiche alors la fin de la plage, et réappelle la fonction avec i+1, correspondant au i suivant la fin de la plage horaire précédente.
	 * @param i int représentant la minute du début de la disponibilité demandé en cours (changeant grâce à la récursivité)
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
	 * on vérifie la validité des paramètres (minuteDebut >=0 et <1440, ainsi que minuteFin <= 1440 et > minuteDebut)
	 * puis on parcours toutes les disponibilités de la plage par une boucle for, et on met à True les disponibilités de la plage.
	 * @param minuteDebut int représentant la minute du début de la plage horaire voulant être rajouté
	 * @param minuteFin int représentant la minute de la fin de la plage horaire voulant être rajouté
	 */
	public void addDispo(int minuteDebut, int minuteFin){
		if (minuteDebut>=0 && minuteDebut<1440 && minuteFin>minuteDebut){
			for (int i=minuteDebut; i<=minuteFin && i<1440; i++){
				this.touteDisponibilite.get(i).setDispo(true);
			}
		}else{
			System.out.println("Mauvaises disponibilitees en parametre");
		}
	}
	/**
	 * deleteDispo permet d'enlever une plage de disponibilité à un service donné
	 * on vérifie la validité des paramètres (minuteDebut >=0 et <1440, ainsi que minuteFin <= 1440 et > minuteDebut)
	 * puis on parcours la plage horaire à supprimer par une boucle for,
	 * et on met à False les disponibilité de la plage
	 * @param minuteDebut int représentant la minute du début de la plage horaire voulant être supprimé
	 * @param minuteFin int représentant la minute de fin de la plage horaire voulant être supprimé
	 */
	public void deleteDispo(int minuteDebut, int minuteFin){
		if (minuteDebut>=0 && minuteDebut<1440 && minuteFin>minuteDebut){
			for (int i=minuteDebut; i<=minuteFin && i<1440; i++){
				this.touteDisponibilite.get(i).setDispo(false);
			}
		}else{
			System.out.println("Mauvaises disponibilitees en parametre");
		}
	}
	
	/**
	 * createDispo met toutes les disponibilité d'un service à la valeure du paramètre
	 * On parcours toutes les disponibilités par une boucle for, et on met les valeurs égales à celle placé en paramètre
	 * @param booléan représentant si on veut initialisé la disponibilité totale d'un service à true ou false
	 */
	public void createDispo(boolean is){
		for(int i=0; i<1440; i++){
			Disponibilite dispo = new Disponibilite(i, is);
			this.touteDisponibilite.add(dispo);
		}
	}
	
	/**
	 * isDispo renvoie si le service donnée est disponible à la minute placé en paramètre
	 * isDispo appelle tout simplement un getter sur "disponible" d'une disponibilite de la collection "touteDisponibilite"
	 * @param minute int représente la minute à laquelle nous voulont savoir si le service est disponible
	 * @return boolean représentant si le service est disponible
	 */
	public boolean isDispo(int minute){
		return this.touteDisponibilite.get(minute).isDispo();
	}
}
