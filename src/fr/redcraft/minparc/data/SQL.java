package fr.redcraft.minparc.data;

import java.io.File;

import fr.redcraft.minparc.Core;


public class SQL extends Database{
	
	public SQL(String db) {
		super(Core.getInstance().getDataFolder()+"/data/"+db);
	}
	
	public static File getDatabase() {
		Data config = new Data();
		return new File(Core.getInstance().getDataFolder()+"/data/"+config.getDBName());
	}

}