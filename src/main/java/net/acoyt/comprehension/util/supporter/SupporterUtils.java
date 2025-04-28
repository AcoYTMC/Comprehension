package net.acoyt.comprehension.util.supporter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.acoyt.comprehension.util.CompUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupporterUtils {
    public static boolean IS_SUPPORTER_REQUIRED = false;

    // Fetches the list of Supporters from the url
    public static List<PlayerInfo> fetchPlayers() {
        List<PlayerInfo> players = new ArrayList<>();
        try {
            // Supporter List URL
            URL url = new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/players.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                // check if "players" exists, and is an array
                if (jsonObject.has("players") && jsonObject.get("players").isJsonArray()) {
                    JsonArray playerArray = jsonObject.getAsJsonArray("players");

                    for (var element : playerArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        players.add(new PlayerInfo(uuid, username));
                    }
                } else {
                    CompUtils.LOGGER.error("Error: 'players' field is missing, or is not an array!");
                }
                reader.close();
            } else {
                CompUtils.LOGGER.error("HTTP Error: " + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Checks if the given player is a supporter
    public static boolean isPlayerSupporter(PlayerEntity player) {
        for (PlayerInfo playerInfo : fetchPlayers()) {
            if (player.getUuidAsString().equals(playerInfo.uuid())) {
                return true;
            }
        }
        return false;
    }

    // Checks if the given UUID is from a supporter
    public static boolean isUuidFromSupporter(UUID uuid) {
        for (PlayerInfo playerInfo : fetchPlayers()) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }
        return false;
    }

    public static List<PlayerInfo> list = SupporterUtils.fetchPlayers();

    // Sets if being a supporter is required to launch the mod
    public static void setIsSupporterRequired(boolean supporterRequired) {
        IS_SUPPORTER_REQUIRED = supporterRequired;
    }

    // Gets the boolean 'IS_SUPPORTER_REQUIRED'
    public static boolean getIsSupporterRequired() {
        return IS_SUPPORTER_REQUIRED;
    }
}