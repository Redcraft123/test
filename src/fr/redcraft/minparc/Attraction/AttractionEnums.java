package fr.redcraft.minparc.Attraction;


public enum AttractionEnums {

	//Frontierland
	BigThunderMountain("BigThunderMountain", LandsEnums.Frontierland),
	PhantomManor("PhantomManor",LandsEnums.Frontierland),
	SplashMountain("SplashMountain",LandsEnums.Frontierland),
	TirdelOuest("Tirdel'Ouest",LandsEnums.Frontierland),
	WildWest("WildWest",LandsEnums.Frontierland),
	GeyserExpedition("GeyserExpedition",LandsEnums.Frontierland),
	FrontierlandTour("FrontierlandTour",LandsEnums.Frontierland),
	MinParcRailroadFrontierlandStation("MinParcRailroad-FrontierlandStation",LandsEnums.Frontierland),

	//rubix

	CubeAdventure("CubeAdventure",LandsEnums.Rubixworld),
	Pyraminx("Pyraminx",LandsEnums.Rubixworld),
	RubixFalls("RubixFalls",LandsEnums.Rubixworld),
	planetExplo("PlanetExploration",LandsEnums.Rubixworld),
	MinParcRailroadRubiksStation("MinParcRailroadRubikStation",LandsEnums.Rubixworld),

	//Disco

	Orbitron("Orbitron",LandsEnums.Discoveryland),
	DiscoverylandRailRoad("MinParcRailroad-Discoveryland Station",LandsEnums.Discoveryland),


	//Ms

	DisneylandTransportation("DisneylandTransportation",LandsEnums.Mainstreet),
	Aquarium("Aquarium",LandsEnums.Mainstreet),
	GrandRoue("GrandeRoue",LandsEnums.Mainstreet),
	RailRoadMainStreet("MinParcRailroad-MainStreet",LandsEnums.Mainstreet),

	//fantasyland
	Symbolica("Symbolica",LandsEnums.Fantasyland),
	Blancheneige("Blanche-neiges",LandsEnums.Fantasyland),
	
	
	//liberty
	Storyofliberty("StoryOfLiberty",LandsEnums.Libertysquare),
	hth("TheHollywoodTowerHotel",LandsEnums.Hollywood);

	public final String nom;

	private AttractionEnums(String nom, LandsEnums land) { this.nom = nom; }


	public String getName(){
		return nom;
	}


	public static AttractionEnums getAttraByID(String str) {
		for (AttractionEnums me : AttractionEnums.values()) {
			if (me.name().equalsIgnoreCase(str))
				return me;
		}
		return null;
	}

	public static AttractionEnums getAttraByName(String str) {
		for (AttractionEnums me : AttractionEnums.values()) {
			if (me.nom.equalsIgnoreCase(str))
				return me;
		}
		return null;
	}

}

