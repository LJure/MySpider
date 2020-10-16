import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Stream2String {
    public String getStreamString(InputStream inputstream){
        if (inputstream != null){
            try{
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
                StringBuilder stringbuffer = new StringBuilder();
                String sTempOneLine = new String("");
                while ((sTempOneLine = bufferedreader.readLine()) != null) {
                    stringbuffer.append(sTempOneLine + "\n");
                }
                return stringbuffer.toString();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
}

