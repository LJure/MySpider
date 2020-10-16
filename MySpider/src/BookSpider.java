import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookSpider {
    public void Start(int p,String fpath){
        String html=new BookHtml().getHtmlByUrl(new GetURL().Get(p));
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            String[] names = new String[500];
            String[] quotes = new String[500];
            String[] imgurls = new String[500];
            float[] ratings = new float[500];
            int kase = 0;
            Elements titles = doc.getElementsByTag("a");
            for(Element elem:titles){
                String title = elem.attr("title");
                if(title != null && !title.equals("")){
                    names[kase++] = title;
                }
            }
            kase = 0;
            Elements rating_num = doc.getElementsByClass("rating_nums");
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
            Elements links = doc.select("[width=90]");
            for(Element link:links){
                String linkHref = link.attr("src");
                imgurls[kase++] = linkHref;
            }

            for (int i = 0; i < 25; ++i) {
               WriteString ws=new WriteString();
               ws.FileWriter(fpath,"书名:"+names[i]);
               ws.FileWriter(fpath,"  豆瓣评分:"+ratings[i]);
               ws.FileWriter(fpath,"  书评:"+quotes[i]+"\n");
               ws.FileWriter(fpath,"封面链接:"+imgurls[i]+"\n");
            }
        }
    }
}
