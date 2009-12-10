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
	 * Le service n'est pas disponible par d�faut
	 * Nous initialisons les disponibilit� du service � false.
	 * @param serv String repr�sentant le nom du service
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
	 * afficherDispo affiche dans la console les disponibilit� d'un service
	 * Le fonction doit �tre appell�e avec 0 pour afficher la disponibilit� de toute la journ�e
	 * On v�rifie que le i ne d�passe pas la journ�e durant toute la fonction, si c'est le cas, soit on affiche la fin de la journ�e, soit on n'affiche rien.
	 * On parcours les disponibilit�s du service, et d�s que l'on trouve une plage disponible, on affiche le premier i de la plage,
	 * puis on incr�mente i jusqu'� trouver la fin de la plage horaire,
	 * on affiche alors la fin de la plage, et r�appelle la fonction avec i+1, correspondant au i suivant la fin de la plage horaire pr�c�dente.
	 * @param i int repr�sentant la minute du d�but de la disponibilit� demand� en cours (changeant gr�ce � la r�cursivit�)
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
	 * on v�rifie la validit� des param�tres (minuteDebut >=0 et <1440, ainsi que minuteFin <= 1440 et > minuteDebut)
	 * puis on parcours toutes les disponibilit�s de la plage par une boucle for, et on met � True les disponibilit�s de la plage.
	 * @param minuteDebut int repr�sentant la minute du d�but de la plage horaire voulant �tre rajout�
	 * @param minuteFin int repr�sentant la minute de la fin de la plage horaire voulant �tre rajout�
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
	 * deleteDispo permet d'enlever une plage de disponibilit� � un service donn�
	 * on v�rifie la validit� des param�tres (minuteDebut >=0 et <1440, ainsi que minuteFin <= 1440 et > minuteDebut)
	 * puis on parcours la plage horaire � supprimer par une boucle for,
	 * et on met � False les disponibilit� de la plage
	 * @param minuteDebut int repr�sentant la minute du d�but de la plage horaire voulant �tre supprim�
	 * @param minuteFin int repr�sentant la minute de fin de la plage horaire voulant �tre supprim�
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
	 * createDispo met toutes les disponibilit� d'un service � la valeure du param�tre
	 * On parcours toutes les disponibilit�s par une boucle for, et on met les valeurs �gales � celle plac� en param�tre
	 * @param bool�an repr�sentant si on veut initialis� la disponibilit� totale d'un service � true ou false
	 */
	public void createDispo(boolean is){
		for(int i=0; i<1440; i++){
			Disponibilite dispo = new Disponibilite(i, is);
			this.touteDisponibilite.add(dispo);
		}
	}
	
	/**
	 * isDispo renvoie si le service donn�e est disponible � la minute plac� en param�tre
	 * isDispo appelle tout simplement un getter sur "disponible" d'une disponibilite de la collection "touteDisponibilite"
	 * @param minute int repr�sente la minute � laquelle nous voulont savoir si le service est disponible
	 * @return boolean repr�sentant si le service est disponible
	 */
	public boolean isDispo(int minute){
		return this.touteDisponibilite.get(minute).isDispo();
	}
}
