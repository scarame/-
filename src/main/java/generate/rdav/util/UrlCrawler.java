package generate.rdav.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlCrawler {
    public  static  void getUrl(String baseUrl){
        Map<String,Boolean> oldMap =new LinkedHashMap<String, Boolean>();
        String oldLinkHost="";
        Pattern p= Pattern.compile("(https?://)?[^/\\s]*");
        Matcher m=p.matcher(baseUrl);
        if(m.find()){
            oldLinkHost=m.group();
        }
        oldMap.put(baseUrl,false);
        oldMap=crawlLinks(oldLinkHost,oldMap);
    }
    //==============================遍历网站所有url===================================================
    public static Map<String,Boolean>  crawlLinks(String oldLinkHost,Map<String,Boolean> oldMap){
        Map<String,Boolean> newMap =new LinkedHashMap<String, Boolean>();
        String oldLink="";
        for(Map.Entry<String,Boolean> mapping : oldMap.entrySet()){
            System.out.println("链接："+mapping.getKey());
            if(!mapping.getValue()){}
            oldLink=mapping.getKey();
            try{
                URL url=new URL(oldLink);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                if(connection.getResponseCode()==200){
                    BufferedReader reader= new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    Pattern p= Pattern.compile("<a.*?href=[\"']?((https?://)?/?[^\"']+)[\"']?.*?>(.+)</a>");
                    Matcher matcher =null;
                    String line ="";
                    while((line=reader.readLine())!=null){
                        matcher=p.matcher(line);
                        if(matcher.find()){
                            String newLink=matcher.group(1).trim();
                            if(!newLink.startsWith("http")){
                                if(newLink.startsWith("/")){
                                    newLink =oldLinkHost +newLink;
                                }else {
                                    newLink =oldLinkHost+"/"+newLink;
                                }
                            }
                            if(newLink.endsWith("/")){
                                newLink =newLink.substring(0,newLink.length()-1);
                            }
                            if(!oldMap.containsKey(newLink)
                                &&!newMap.containsKey(newLink)
                                && newLink.startsWith(oldLinkHost)){
                                newMap.put(newLink,false);
                            }
                        }
                    }
                }
            }catch (Exception e){

            }finally {

            }
            oldMap.replace(oldLink,false,true);
        }
        if(!newMap.isEmpty()){
            oldMap.putAll(newMap);
            oldMap.putAll(crawlLinks(oldLinkHost, oldMap));
        }
        return oldMap;
    }
    //=========================================获取指定Url=========================================
    public static void crawler2(String url,int start) {
        infoCrawler infoC=new infoCrawler();
        //页面对象，内涵页面所有元素信息
        try {
            Document document= Jsoup.connect(url).get();
            Elements element=document.select("div.content__pg");
            //System.out.println(element);
            String dateUrl= element.attr("data-url").substring(0,10);

            int maxIndex= Integer.parseInt(element.attr("data-totalpage"));
            for(int i=start;i<=maxIndex;i++){
                System.out.println("<--->正在获取"+i+"页<--->");
                infoC.contentCrawl(url,dateUrl+i);
            }
        }catch (IOException e){

        }

    }

}
