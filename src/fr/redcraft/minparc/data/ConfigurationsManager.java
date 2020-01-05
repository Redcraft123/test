package fr.redcraft.minparc.data;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationsManager
{
  private static String path = "plugins/MinparcCore";
  private static File f = new File(path);
  private static File messagesFile;
  private static FileConfiguration messagesConfig;
  private static File tntLogFile;
  private static FileConfiguration tntLogsConfig;
  
  public static void setupAllConfig()
  {
    if (!f.exists()) {
      f.mkdirs();
    }
    messagesFile = new File(path, "automessages.yml");
    if (!messagesFile.exists()) {
      try
      {
        messagesFile.createNewFile();
      }
      catch (IOException e)
      {
        System.out.println("Erreur lors de la creation du fichier de configuration \"" + messagesConfig.getName().toString() + "\" !");
        return;
      }
    }
    messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    
    tntLogFile = new File(path, "tntlogs.yml");
    if (!tntLogFile.exists()) {
      try
      {
        tntLogFile.createNewFile();
      }
      catch (IOException e)
      {
        System.out.println("Erreur lors de la creation du fichier de configuration \"" + tntLogFile.getName().toString() + "\" !");
        return;
      }
    }
    tntLogsConfig = YamlConfiguration.loadConfiguration(tntLogFile);
  }
  
  public static void saveAllConfig()
  {
    try
    {
      messagesConfig.save(messagesFile);
    }
    catch (IOException e)
    {
      System.out.println("Erreur lors de la sauveguarde du fichier de configuration \"" + messagesConfig.getName().toString() + "\"");
    }
    try
    {
      tntLogsConfig.save(tntLogFile);
    }
    catch (IOException e)
    {
      System.out.println("Erreur lors de la sauveguarde du fichier de configuration \"" + tntLogsConfig.getName().toString() + "\"");
    }
  }
  
  public static FileConfiguration getMessagesConfig()
  {
    return messagesConfig;
  }
  
  public static FileConfiguration getTntLogConfig()
  {
    return tntLogsConfig;
  }
}
