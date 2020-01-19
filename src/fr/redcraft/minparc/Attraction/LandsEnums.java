package fr.redcraft.minparc.Attraction;


public enum LandsEnums {
       
    	//Frontierland
    	Mainstreet("MainStreet"),
    	Frontierland("Frontierland"),
    	Discoveryland("Discoveryland"),
    	Fantasyland("Fantasyland"),
    	Rubixworld("RubixWorld"),
    	Libertysquare("LibertySquare"),
    	Neworléanssquare("NewOrléansSquare"),
    	Hollywood("Hollywood");

       
        public final String nom;
       
        private LandsEnums(String nom) { this.nom = nom; }
       
        
        public String getName(){
            return nom;
        }
        
        
        public static LandsEnums getLandByID(String str) {
            for (LandsEnums me : LandsEnums.values()) {
                if (me.name().equalsIgnoreCase(str))
                    return me;
            }
            return null;
        }
       
        public static LandsEnums getLandByName(String str) {
            for (LandsEnums me : LandsEnums.values()) {
                if (me.nom.equalsIgnoreCase(str))
                    return me;
            }
            return null;
        }
       
    }
   
