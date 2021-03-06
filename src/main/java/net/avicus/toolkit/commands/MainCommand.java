package net.avicus.toolkit.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.NestedCommand;
import net.avicus.toolkit.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MainCommand {

  @Command(aliases = {
      "xml"}, desc = "Various commands that help generate the map.xml", min = 0, max = -1)
  @NestedCommand({XMLCommands.class})
  public static void xml(final CommandContext args, CommandSender sender) throws CommandException {
  }

  @Command(aliases = {"help"}, desc = "AtlasToolkit help", min = 0, max = -1)
  public static void help(final CommandContext args, CommandSender sender) throws CommandException {
    if (args.getInteger(0, 1) > 2) {
      throw new CommandException("Unknown help page. Max pages: 2");
    }
    sender.sendMessage(StringUtils
        .padMessage("AtlasToolkit Help (" + args.getInteger(0, 1) + "/2)", "-",
            ChatColor.STRIKETHROUGH, ChatColor.GREEN));
    if (args.getInteger(0, 1) == 1) {
      sender.sendMessage(ChatColor.LIGHT_PURPLE + "Item XML: ");
      sender.sendMessage(
          "    " + ChatColor.AQUA + "/toolkit xml inventory - Export your inventory to xml");
      sender.sendMessage("    " + ChatColor.AQUA + "/toolkit xml item - Export your item to xml");
      sender
          .sendMessage("    " + ChatColor.AQUA + "/toolkit xml parseitem - Load an item from xml");
    } else {
      sender.sendMessage(ChatColor.LIGHT_PURPLE + "XML Generating: ");
      sender.sendMessage("    " + ChatColor.AQUA
          + "/toolkit gen point - Generate a point based on where you are currently standing");
      sender.sendMessage("    " + ChatColor.AQUA
          + "/toolkit gen cuboid - Generate a cuboid from the WorldEdit selection");
      sender.sendMessage("    " + ChatColor.AQUA
          + "/toolkit gen circle - Generate a circle based on your current position");
      sender.sendMessage("    " + ChatColor.AQUA
          + "/toolkit gen cylinder - Generate a cylinder based on your current position");
      sender.sendMessage(
          "    " + ChatColor.AQUA + "/toolkit gen upload - Upload all generated regions to DPaste");
    }
  }

  @Command(aliases = {"generate",
      "gen"}, desc = "Functions for generating xml regions", min = 1, max = 1)
  @NestedCommand({GenerateCommands.class})
  public static void generate(final CommandContext args, CommandSender sender)
      throws CommandException {
  }

  public static class MainParentCommand {

    @Command(aliases = {"toolkit", "tk", "pgmtoolkit",
        "pgmtk"}, desc = "All AtlasToolkit commands", min = 0, max = -1)
    @NestedCommand({MainCommand.class})
    public static void toolkit(final CommandContext args, CommandSender sender)
        throws CommandException {
    }
  }
}