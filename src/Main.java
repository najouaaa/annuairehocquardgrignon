public class Main {
	public static void main(String args[]) {
		//Création des pages jaunes, avec rajouts de qques villes (relais)
		Annuaire PagesJaunes = new Annuaire();
		PagesJaunes.addRelais("Nantes", 15.4, 45);
		PagesJaunes.addRelais("Paris", 87, 24);
		PagesJaunes.addRelais("Lyon", 110.5, 20.356);
		PagesJaunes.addRelais("Marseille", 105, 230);
		
		//rajout des services pour les relais avec 
		PagesJaunes.prendRelais("Nantes").addService("restaurant", 14, 245);
		PagesJaunes.prendRelais("Nantes").addService("hotel", 235, 623);
		PagesJaunes.prendRelais("Nantes").addService("bar", 125, 300);
		PagesJaunes.prendRelais("Nantes").addService("essence", 675, 1145);
		PagesJaunes.prendRelais("Nantes").addService("taxi",765, 910);

		PagesJaunes.prendRelais("Nantes").prendService("bar").addDispo(400, 800);
		
		PagesJaunes.prendRelais("Paris").addService("restaurant", 519, 1024);
		PagesJaunes.prendRelais("Paris").addService("hotel",156,174);
		PagesJaunes.prendRelais("Paris").addService("bar",290, 412);
		PagesJaunes.prendRelais("Paris").addService("essence",571,981);
		PagesJaunes.prendRelais("Paris").addService("taxi",764, 910);

		PagesJaunes.prendRelais("Lyon").addService("restaurant",280, 1190);
		PagesJaunes.prendRelais("Lyon").addService("hotel", 581, 832);
		
		PagesJaunes.prendRelais("Marseille").addService("football", 35,929);
		PagesJaunes.prendRelais("Marseille").addService("bar", 0, 1600);
		
		//retrait de deux service et d'un relais
		PagesJaunes.prendRelais("Paris").removeService("restaurant");
		PagesJaunes.prendRelais("Paris").removeService("essence");
		PagesJaunes.removeRelais("Lyon");
		
		//affichage des relais :
		PagesJaunes.afficherTout();
		
		//Deux relais égaux ?
		System.out.println("Les relais Paris et Nantes sont-ils égaux ?\n"+PagesJaunes.prendRelais("Paris").servicesIsEqual(PagesJaunes.prendRelais("Nantes")));
		
		//Distance de Nantes à un point donné :
		System.out.println("Le relais Nantes se trouve à : "+PagesJaunes.prendRelais("Nantes").distanceTo(35, 93)+"kms du point x:35, y=93");
		
		//Affichage des relais qui ont le service bar
		PagesJaunes.afficheRelais("taxi");
		
		//Relais le plus proche ayant le service, dans la dispo, à ces coordonnées
		System.out.println("Le relais le plus proche est "+PagesJaunes.chercheRelais("bar", 300, 15, 75).getNomRelais()+" à "+PagesJaunes.chercheRelais("bar", 300, 15, 75).distanceTo(15,75)+"kms");
		
		
		
		
		Annuaire PagesJaunes_ = new Annuaire();
		PagesJaunes_.addRelais("Nantes", 15.4, 45);
		PagesJaunes_.addRelais("Paris", 87, 24);
		PagesJaunes_.addRelais("Lyon", 110.5, 20.356);
		PagesJaunes_.addRelais("Marseille", 105, 230);
		
		//rajout des services pour les relais avec 
		PagesJaunes_.prendRelais("Nantes").addService("restaurant", 14, 245);
		PagesJaunes_.prendRelais("Nantes").addService("hotel", 235, 623);
		PagesJaunes_.prendRelais("Nantes").addService("bar", 125, 300);
		PagesJaunes_.prendRelais("Nantes").addService("essence", 675, 1145);
		PagesJaunes_.prendRelais("Nantes").addService("taxi",765, 910);

		PagesJaunes_.prendRelais("Nantes").prendService("bar").addDispo(400, 800);
		
		PagesJaunes_.prendRelais("Paris").addService("restaurant", 519, 1024);
		PagesJaunes_.prendRelais("Paris").addService("hotel",156,174);
		PagesJaunes_.prendRelais("Paris").addService("bar",290, 412);
		PagesJaunes_.prendRelais("Paris").addService("essence",571,981);
		PagesJaunes_.prendRelais("Paris").addService("taxi",764, 910);

		PagesJaunes_.prendRelais("Lyon").addService("restaurant",280, 1190);
		PagesJaunes_.prendRelais("Lyon").addService("hotel", 581, 832);
		
		//PagesJaunes_.prendRelais("Marseille").addService("football", 35,929);
		//PagesJaunes_.prendRelais("Marseille").addService("bar", 0, 1600);
		
		//retrait de deux service et d'un relais
		PagesJaunes_.prendRelais("Paris").removeService("restaurant");
		PagesJaunes_.prendRelais("Paris").removeService("essence");
		PagesJaunes_.removeRelais("Lyon");
		System.out.println(PagesJaunes_.isEqual1(PagesJaunes));
	}
}
