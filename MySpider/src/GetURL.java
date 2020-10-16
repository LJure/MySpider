public class GetURL {
    public String Get(int i){
        String url = "";
        String xhtml = String.valueOf(i * 25);
        url = "?start=" + xhtml;
        return url;
    }
}

