import java.io.FileWriter;
import java.io.IOException;

public class WriteString {
    public void FileWriter(String path,String content){
        FileWriter file=null;
        try{
            file=new FileWriter(path,true);
            file.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert file != null;
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

