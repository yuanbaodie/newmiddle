package org.htmpParse;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyx on 2016/8/19.
 */
public class HtmlParse {
    public static void parse(String html){

            Parser parser =  Parser.createParser(html, "UTF-8");
            HtmlPage htmlpage = new HtmlPage(parser);
            System.out.println(htmlpage.getBody());
//            for (NodeIterator i = parser.elements (); i.hasMoreNodes(); ) {
//                Node node = i.nextNode();
//                System.out.println("node.toPlainTextString())="+node.toPlainTextString());
//            }


    }

    public static String openFile( String szFileName ) {
        try {
            BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream( new File(szFileName)), "GBK") );
            String szContent="";
            String szTemp;

            while ( (szTemp = bis.readLine()) != null) {
                szContent+=szTemp+"\n";
            }
            bis.close();
            return szContent;
        }
        catch( Exception e ) {
            return "";
        }
    }

    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;


        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            String regEx ="<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

            p_style = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }

    public static String cancelHh(String text){
       // String returnRegEx ="(\r\n|\r|\n|\n\r)";
        String returnRegEx ="\\\\s*|\\t|\\r|\\n";
        java.util.regex.Pattern p_return;
        java.util.regex.Matcher m_return;
        p_return = Pattern.compile(returnRegEx, Pattern.CASE_INSENSITIVE);
        m_return = p_return.matcher(text);
       //return  m_return.replaceAll("").replaceAll("\\s*", " ");
       return  m_return.replaceAll("");
    }

    public static String getHtmlHeader(String html){
        String regEx ="(?>=(<[\\s]*?body[^>]*?>))([\\s\\S]*?)(?=(<[\\s]*?\\/[\\s]*?body[\\s]*?>))";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(html);
        String head = "";
        while (matcher.find()) {
            head = matcher.group();
        }
        return head;
    }

    public static void main(String[] args) {
//        String html = openFile("D:/123.txt");
//        System.out.println(cancelHh(Html2Text(html).trim()));
        String html="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n" +
                "  <title></title>\n" +
                "  <meta name=\"Generator\" content=\"Cocoa HTML Writer\">\n" +
                "  <meta name=\"CocoaVersion\" content=\"1504.76\">\n" +
                "  <style type=\"text/css\">\n" +
                "    p.p1 {margin: 0.0px 0.0px 0.0px 0.0px; line-height: 14.0px; font: 12.0px Helvetica; color: #000000; -webkit-text-stroke: #000000}\n" +
                "    span.s1 {font-kerning: none}\n" +
                "    span.s2 {font: 12.0px 'PingFang SC'; font-kerning: none}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body class='222'>hhhhwo</body>";
        String head = getHtmlHeader(html);
        System.out.println(head);
    }
}
