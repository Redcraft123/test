package fr.redcraft.minparc.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.Lists;

import fr.redcraft.minparc.Attraction.AttractionEnums;



public class TabulationExecutor implements TabCompleter {

	public List<String> onTabComplete(CommandSender s, Command c, String l, String[] a)
	  {
		if(c.getName().equalsIgnoreCase("minparc attraction")) {
	    List<String> tabComplete = Lists.newArrayList();
	    
	  
	    
	    if (a.length == 2)
	    {
	      AttractionEnums[] arrayOfRankList;
	      int j = (arrayOfRankList = AttractionEnums.values()).length;
	      for (int i = 0; i < j; i++)
	      {
	        AttractionEnums rankList = arrayOfRankList[i];
	        if (rankList.getName().toLowerCase().startsWith(a[1].toLowerCase())) {
	          tabComplete.add(rankList.getName());
	        }
	      }
	      return tabComplete;
	    }
	    return null;
	  }
		return null;
	}
}


