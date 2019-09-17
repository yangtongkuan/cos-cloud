package com.cos.cloud.common.tools;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 功能：XML解析工具类
 *
 * @author lmb
 * @version 1.0
 * @date 2017-6-5
 */
public class ParseXMLUtils {

    // 必须有一个根节点才可以
    public static Map<String, Object> xmlToMap(String xml) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
            Map map = Dom2Map(document);
            System.out.println("map>>> " + map);
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("xml解析map失败->原文件:" + xml);
        }
        return new HashMap<>();
    }

    /**
     * 将Document对象转为Map（String→Document→Map）
     *
     * @param
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Object> Dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null) {
            return map;
        }
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            if (e.elements().size() > 0) {
                map.put(e.getName(), Dom2Map(e));
            } else {
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }

    /**
     * 将Element对象转为Map（String→Document→Element→Map）
     *
     * @param
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Map Dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (Element iter : ((List<Element>) list)) {
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());//公共map resultCode=0
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

    public static void main(String[] args) {
        String str1 = "<HEADER>" +
//                "       <POOL_ID>2</POOL_ID>" +
                "       <a>" +
                "       <DB_ID><![CDATA[SUCCESS]]></DB_ID>" +
                "       <DB_ID>EUe</DB_ID>" +
                "<DB_ID><b>b</b><c>ccc</c></DB_ID>" +
                "</a>" +
//                "       <CHANNEL_ID>11</CHANNEL_ID>" +
//                "       <USERNAME>tom</USERNAME>" +
//                "       <PASSWORD>sss</PASSWORD>" +
                "   </HEADER>";
        String str2 = "<ROOT>" +
                "  <HEADER>" +
                "      <POOL_ID>2</POOL_ID>" +
                "      <CHANNEL_ID>11</CHANNEL_ID>" +
                "      <USERNAME>tom</USERNAME>" +
                "      <PASSWORD>sss</PASSWORD>" +
                "  </HEADER>" +
                "  <BODY>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>7107300212</PHONE_NO>" +
                "          <TRACE_ID>97D2C7D26224A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>EUR</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>2222300212</PHONE_NO>" +
                "          <TRACE_ID>444424A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>USA</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "  </BODY>" +
                "</ROOT>";
//        System.out.println();
        Long sTime = DateUtils.getCurrentTimeMillis();
        xmlToMap(str1);
        System.out.println(DateUtils.getCurrentTimeMillis() - sTime);
    }
}