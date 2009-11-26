import java.util.ArrayList;
import java.util.Iterator;


public class Service {
	ArrayList<Disponibilite> touteDisponibilite = new ArrayList<Disponibilite>();
	String nomService;

	
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
	
	// afficher les disponibilit�s d'un service
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
	
	//ajouter un intervalle de disponibilit�
	public void addDispo(int minuteDebut, int minuteFin){
		if (minuteDebut>=0){
			for (int i=minuteDebut; i<=minuteFin && i<1440; i++){
				this.touteDisponibilite.get(i).setDispo(true);
			}
		}else{
			System.out.println("Mauvaises disponibilitees en parametre");
		}
	}
	//surpprimer un intervalle de disponibilit�
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
