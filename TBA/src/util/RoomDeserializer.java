package util;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import world.Room;

public class RoomDeserializer implements JsonDeserializer<Room> {

	@Override
	public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		System.out.println(obj);
		String name = "";
		String abbrev = "";
		String desc = "";
		ArrayList<String> loc = new ArrayList<String>();

		JsonArray tempObj = obj.get("loc").getAsJsonArray();
		Gson conv = new Gson();
		Type type = new TypeToken<ArrayList<String>>() {
		}.getType();
		loc = conv.fromJson(tempObj, type);

		name = obj.get("name").getAsString();

		abbrev = obj.get("abbrev").getAsString();

		desc = obj.get("desc").getAsString();

		return new Room(name, abbrev, desc, loc);
	}

}
