/**
 * Disponibilite repr�sente la disponibilit� d'une minute durant la journ�e
 * @author Hocquard Grignon
 */
public class Disponibilite {
	int minute;
	boolean dispo;

	/**
	 * Le constructeur d'une disponibilit� s'initialise � false;
	 */
	public Disponibilite(){
		dispo = false;
	}
	/**
	 * Le constructeur surcharg� de la minute, ainsi que de la disponibilit�, permet de mettre la disponibilit� � true ou false selon le param�tre
	 * @param minute int repr�sentant la minute de disponibilit�
	 * @param dispo boolean repr�sentant true si le service est disponible � cette disponibilit�
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
