import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;

public class BookHtml {
    public String getHtmlByUrl(String url){
        String html = null;
        url="https://book.douban.com/top250"+url;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("Accept", "text/html");
        httpget.addHeader("Accept-Charset", "utf-8");
        httpget.addHeader("Accept-Encoding", "gzip");
        httpget.addHeader("Accept-Language", "zh-CN,zh");
        httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64)" + " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
        RequestConfig config=RequestConfig.custom()
                .setConnectTimeout(10000) //设置连接超时时间为10s
                .setSocketTimeout(10000) //设置读取超时时间为10s
                .build();
        httpget.setConfig(config);
        try {
            //得到responce对象
            HttpResponse responce = httpClient.execute(httpget);
            int ResStatus = responce.getStatusLine().getStatusCode();
            if (ResStatus== HttpStatus.SC_OK) {
                //获得输入流
                InputStream entity = responce.getEntity().getContent();
                if (entity!=null) {
                    html=new Stream2String().getStreamString(entity);
                //此处输出html
                    System.out.println(html);
                }
            }
        } catch (Exception e) {
            System.out.println("书籍页访问出现异常!");
            e.printStackTrace();
        }
        return html;
    }
}
