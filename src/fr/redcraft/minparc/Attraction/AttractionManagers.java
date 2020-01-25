package fr.redcraft.minparc.Attraction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.data.SettingsManager;


public class AttractionManagers {
 
    public static void setAttractionTo(AttractionEnums attraction, int status,Player sender) {
        switch (status) {
            case 0: setOuvert(attraction,sender); break;
            case 1: setFermer(attraction,sender); break;
            case 2: setMaintenance(attraction,sender); break;
            
        }
    }
   
    public static void setOuvert(AttractionEnums attraction, Player sender) {
        SettingsManager.getInstance().getData().set("access." + attraction.name(), 0);
        SettingsManager.getInstance().saveData();
        Bukkit.broadcastMessage("§a§lL'attraction §9§l"+attraction.nom+" §a§lest maintenant ouverte");
    }
   
    public static void setFermer(AttractionEnums attraction, Player sender) {
        SettingsManager.getInstance().getData().set("access." + attraction.name(), 1);
        SettingsManager.getInstance().saveData();
        Bukkit.broadcastMessage("§c§lL'attraction §9§l"+attraction.nom+" §c§lest maintenant fermée");
    }
   
    public static void setMaintenance(AttractionEnums attraction, Player sender) {
        SettingsManager.getInstance().getData().set("access." + attraction.name(), 2);
        SettingsManager.getInstance().saveData();
        Bukkit.broadcastMessage("§6§lL'attraction §9§l"+attraction.nom+" §6§lest maintenant en maintenance");
    }
   
    public static boolean getAttraStatus(String id) {
        AttractionEnums attra = AttractionEnums.getAttraByID(id); 
        if (attra != null) {
            int state = SettingsManager.getInstance().getData().getInt("access." + attra.name());
            switch (state) {
            case 0: return "§aOuvert/Open" != null;
            }
        }
        return false;
    }
   
    
    public static String getAttraStatusByName(String name) {
        AttractionEnums attra = AttractionEnums.getAttraByName(name); 
        if (attra != null) {
            return getAttraStatusByID(attra.name());
        }
        return "";
    }
    
    public static String getAttraStatusByID(String id) {
        AttractionEnums attra = AttractionEnums.getAttraByID(id); 
        if (attra != null) {
            int state = SettingsManager.getInstance().getData().getInt("access." + attra.name());
            switch (state) {
                case 0: return "§aOuvert/Open"; 
                case 1: return "§cFermé/close"; 
                case 2: return "§6Maintenance";
            }
        }
        return "";
    }
}