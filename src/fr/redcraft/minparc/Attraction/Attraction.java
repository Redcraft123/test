package fr.redcraft.minparc.Attraction;


public enum Attraction {
       
    	//Frontierland
    	BigThunderMountain("BigThunderMountain"),
    	PhantomManor("PhantomManor"),
    	SplashMountain("SplashMountain"),
    	TirdelOuest("Tirdel'Ouest"),
    	WildWest("WildWest"),
    	GeyserExpedition("GeyserExpedition"),
    	FrontierlandTour("FrontierlandTour"),
    	MinParcRailroadFrontierlandStation("MinParcRailroad-FrontierlandStation"),
    	
    	//rubix
    	
    	CubeAdventure("CubeAdventure"),
    	Pyraminx("Pyraminx"),
    	RubixFalls("RubixFalls"),
    	planetExplo("PlanetExploration"),
    	MinParcRailroadRubiksStation("MinParcRailroadRubikStation"),
    	
    	//Disco
    	
    	Orbitron("Orbitron"),
    	DiscoverylandRailRoad("MinParcRailroad-Discoveryland Station"),
    	
    	
    	//Ms
    	
    	DisneylandTransportation("DisneylandTransportation"),
    	Aquarium("Aquarium"),
    	GrandRoue("GrandeRoue"),
    	RailRoadMainStreet("MinParcRailroad-MainStreet"),
    	
    	//Liberty
    	TowerHotel("TheHollywoodTowerHote"),
    	
    	attra1("BTM"),
        attra2("PM"),
        attra3("CC");
       
        public final String nom;
       
        private Attraction(String nom) { this.nom = nom; }
       
        
        public String getName(){
            return nom;
        }
        
        
        public static Attraction getAttraByID(String str) {
            for (Attraction me : Attraction.values()) {
                if (me.name().equalsIgnoreCase(str))
                    return me;
            }
            return null;
        }
       
        public static Attraction getAttraByName(String str) {
            for (Attraction me : Attraction.values()) {
                if (me.nom.equalsIgnoreCase(str))
                    return me;
            }
            return null;
        }
       
    }
   
