import kong.unirest.HttpResponse;
import org.json.*;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.json.JSONArray;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class movie {

    public static void main(String[] args) throws IOException {
        HttpResponse<String> response = Unirest.get("https://imdb-top-100-movies.p.rapidapi.com/")
                .header("X-RapidAPI-Key", "91999ca8c2mshef0f154e19f8b3ep131088jsn7128170d5bf7")
                .header("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .asString();
        String jsonResponse = response.getBody();

        String[] top10Movies = new String[10], moviePosters = new String[10];

        org.json.JSONArray jsonArray = new JSONArray(jsonResponse);

        for(int i = 0; i < 10; i++) {
            top10Movies[i] = jsonArray.getJSONObject(i).getString("title");
            moviePosters[i] = jsonArray.getJSONObject(i).getString("image");
        }
        for(int i = 0; i < 10; i++) {
            System.out.println(top10Movies[i]);
        }

        JFrame frame = new JFrame();

        DefaultListModel<ImageIcon> listModel = new DefaultListModel<>();
        for (int i = 0; i < 10; i++) {
            URL url = new URL(moviePosters[i]);
            ImageIcon poster = new ImageIcon(ImageIO.read(url));
            listModel.add(i, poster);
        }

        JList<ImageIcon> lsm = new JList<>(listModel);
        lsm.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        lsm.setVisibleRowCount(1);

        frame.add(new JScrollPane(lsm));
        frame.pack();
        frame.setVisible(true);
    }
}