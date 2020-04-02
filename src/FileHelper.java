import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    final static String FILE_PATH = "files/flags.csv";

    List<String> getFileContents() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
