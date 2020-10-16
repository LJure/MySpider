import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieSpider {
    public void Start(int p,String fpath){
        String html=new MovieHtml().getHtmlByUrl(new GetURL().Get(p));
        if (html!=null&&!"".equals(html)){
            Document doc = Jsoup.parse(html);
            String[] names = new String[500];
            String[] quotes = new String[500];
            String[] imgurls = new String[500];
            float[] ratings = new float[500];
            int kase = 0;
            Elements titles = doc.getElementsByClass("title");
            for(Element elem:titles){
                String title = elem.text();
                if(title.charAt(0) != '/') {
                    names[kase++] = title;
                }
            }
            kase = 0;
            Elements rating_num = doc.getElementsByClass("rating_num");
            for(Element rating:rating_num){
                float num = Float.parseFloat(rating.text());
                ratings[kase++] = num;
            }
            kase = 0;
            Elements quos = doc.select("p.quote");
            for(Element quo:quos){
                String text = quo.text();
                quotes[kase++] = text;
            }
            kase = 0;
            Elements links = doc.select("[width=100]");
            for(Element link:links){
                String linkHref = link.attr("src");
                imgurls[kase++] = linkHref;
            }

            for (int i = 0; i < 25; ++i) {
                WriteString ws=new WriteString();
                ws.FileWriter(fpath,"影片名:"+names[i]);
                ws.FileWriter(fpath,"  豆瓣评分:"+ratings[i]);
                ws.FileWriter(fpath,"  影评:"+quotes[i]+"\n");
                ws.FileWriter(fpath,"封面链接:"+imgurls[i]+"\n");
            }
        }
    }
}
