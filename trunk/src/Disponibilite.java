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
	
	/**
	 * getter classique pour minute
	 * @return minute de this
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * setter classique pour minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	/**
	 * setter classique pour dispo
	 * @return booléen de dispo
	 */
	public boolean isDispo() {
		return dispo;
	}
	/**
	 * setter classique pour dispo
	 */
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
}
