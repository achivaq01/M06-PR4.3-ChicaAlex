import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

public class PR143GestioLlibreriaMain {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Llibre> llibres = llegirJSON(new FileReader("./data/llibres_input.json"));

        for (Llibre llibre : llibres) {
            int any;

            if (llibre.getId() == 1) {
                any = 1995;
            } else {
                any = llibre.getAny();
            }

            llibre.setAny(any);
        }

        Llibre nouLlibre = new Llibre(4, "Històries de la ciutat", "Miquel Soler", 2022);
        llibres.add(nouLlibre);
        System.out.println("Llibre afegit amb èxit!");

        int idToDelete = 2;
        for (Llibre llibre : llibres) {
            if (llibre.getId() == idToDelete) {
                llibres.remove(llibre);
                System.out.println("Llibre amb ID " + idToDelete + " esborrat amb èxit!");
                break;
            }
        }

        guardarDades(llibres, "./data/llibres_output.json");
    }

    public static List<Llibre> llegirJSON(Reader reader) {
        List<Llibre> llibres = new ArrayList<>();

        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                int id = jsonObject.getInt("id");
                String titol = jsonObject.getString("títol");
                String autor = jsonObject.getString("autor");
                int any = jsonObject.getInt("any");

                Llibre llibre = new Llibre(id, titol, autor, any);
                llibres.add(llibre);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return llibres;
    }

    public static void guardarDades(List<Llibre> llibres, String outputFile) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Llibre llibre : llibres) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("id", llibre.getId())
                    .add("títol", llibre.getTitol())
                    .add("autor", llibre.getAutor())
                    .add("any", llibre.getAny());

            jsonArrayBuilder.add(jsonObjectBuilder);
        }

        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(outputFile))) {
            jsonWriter.writeArray(jsonArrayBuilder.build());
            System.out.println("Dades guardades amb èxit a " + outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}