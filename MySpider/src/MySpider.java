public class MySpider {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new BookSpider().Start(i,"C:\\Users\\bdure\\Desktop\\spidercontent\\book.txt");
            new MovieSpider().Start(i,"C:\\Users\\bdure\\Desktop\\spidercontent\\movie.txt");
        }
        System.out.println("爬取完成！");
    }
}
