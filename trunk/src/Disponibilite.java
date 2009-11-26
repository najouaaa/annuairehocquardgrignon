
public class Disponibilite {
	// temps exprims en minutes sur une journe (24*60 = 1440 minutes)
	int minute;
	boolean dispo;

	
	public Disponibilite(){
		dispo = false;
	}
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
