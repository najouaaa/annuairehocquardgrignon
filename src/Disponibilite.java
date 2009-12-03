/**
 * Disponibilite représente la disponibilité d'une minute durant la journée
 * @author Hocquard Grignon
 */
public class Disponibilite {
	int minute;
	boolean dispo;

	/**
	 * Le constructeur d'une disponibilité s'initialise à false;
	 */
	public Disponibilite(){
		dispo = false;
	}
	/**
	 * Le constructeur surchargé de la minute, ainsi que de la disponibilité, permet de mettre la disponibilité à true ou false selon le paramètre
	 * @param minute int représentant la minute de disponibilité
	 * @param dispo boolean représentant true si le service est disponible à cette disponibilité
	 */
	public Disponibilite(int minute, boolean dispo){
		this.minute = minute;
		this.dispo = dispo;
	}
	
	//getters and setters
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public boolean isDispo() {
		return dispo;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	
}
