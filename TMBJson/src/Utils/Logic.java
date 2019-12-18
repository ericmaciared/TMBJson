package Utils;

import DataModel.DataModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Logic {
    final private String PATH = "TMBJson/data/localitzacions.json";

    private DataModel data = new DataModel();

    public void loadLocations() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;

        reader = new JsonReader(new FileReader(PATH));
        data = gson.fromJson(reader, DataModel.class);

    }
}
