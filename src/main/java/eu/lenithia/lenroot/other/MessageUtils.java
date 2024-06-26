package eu.lenithia.lenroot.other;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static String replacePlaceholders(String text, Player player) {
        text = (text == null) ? "An error occurred somewhere! (messages.yml)" : text;
        return PlaceholderAPI.setPlaceholders(player, text);
    }

    public static Component deserialize(String text, Player player) {
        text = replacePlaceholders(text, player);

        return MiniMessage.miniMessage().deserialize(text);
    }

    public static Component deserialize(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }


}
