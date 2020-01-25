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
	MinParcRailroadFrontierlandStation("MinParcRailroadFrontierlandStation",LandsEnums.Frontierland),

	//rubix

	CubeAdventure("CubeAdventure",LandsEnums.Rubixworld),
	Pyraminx("Pyraminx",LandsEnums.Rubixworld),
	RubixFalls("RubixFalls",LandsEnums.Rubixworld),
	PlanetExploration("PlanetExploration",LandsEnums.Rubixworld),
	MinParcRailroadRubiksStation("MinParcRailroadRubikStation",LandsEnums.Rubixworld),

	//Disco

	Orbitron("Orbitron",LandsEnums.Discoveryland),
	DiscoverylandRailRoad("MinParcRailroadDiscoveryland Station",LandsEnums.Discoveryland),


	//Ms

	DisneylandTransportation("DisneylandTransportation",LandsEnums.Mainstreet),
	Aquarium("Aquarium",LandsEnums.Mainstreet),
	GrandRoue("GrandRoue",LandsEnums.Mainstreet),
	MinParcRailroadMainStreet("MinParcRailroadMainStreet",LandsEnums.Mainstreet),

	//fantasyland
	Symbolica("Symbolica",LandsEnums.Fantasyland),
	Blancheneige("Blancheneiges",LandsEnums.Fantasyland),
	
	
	//liberty
	Storyofliberty("StoryOfLiberty",LandsEnums.Libertysquare),
	TheHollywoodTowerHotel("TheHollywoodTowerHotel",LandsEnums.Hollywood),
	DisneyLandTransportatonLiberty("DisneyLandTransportatonLiberty",LandsEnums.Mainstreet);

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

