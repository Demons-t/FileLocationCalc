import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

public class XMLUtils {
    // 获取 Document 对象
    public static Document getDocument(String path){
        try {
            SAXReader reader = new SAXReader();
            FileInputStream in = new FileInputStream(path);
            Document doc = reader.read(in);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取指定节点的元素
    public static Element getElement(Document doc, String name){
        try {
            Element root = doc.getRootElement();
            if (root.getName().equals(name))
                return root;
            return getChildElement(root,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取指定节点的子元素
    private static Element getChildElement(Element root, String name){
        try {
            List<Element> childNodes = root.elements();
            for (Element e : childNodes) {
//                System.out.println(e.getName());
                if (e.getName().equals(name))
                    return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 获取指定元素的属性
    public static Attribute find(Element element, String name){
        List<Attribute> attrs = element.attributes();
        if (attrs != null && attrs.size() > 0) {
            for (Attribute attr : attrs) {
//                System.out.println(attr.getName());
                if (attr.getName().equals(name)){
                    return attr;
                }
            }
        }
        return null;
    }

    public static void changleApplication(String xmlPath){
        // 1. 获取文档对象
        Document doc = XMLUtils.getDocument(xmlPath);
        // 2. 获取指定元素
        Element element = XMLUtils.getElement(doc,"application");
        // 3. 获取属性
        Attribute attribute = XMLUtils.find(element,"name");
        if(attribute == null){
//            System.out.println("attribute is null");
            // 添加属性
            element.addAttribute("android:name", "com.example.dummydex.DummyApplication");
        } else {
//            System.out.println(attribute.getName() +":"+ attribute.getValue());
            // 获取源属性的值，即Application类
            String name = attribute.getValue();
            // 设置新的值
            attribute.setValue("com.example.dummydex.DummyApplication");
            // 将老的值保存到 meta-data节点中
            Element element1 = element.addElement("meta-data");
            element1.addAttribute("android:name", "SRC_APPLICATION");
            element1.addAttribute("android:value", name);
        }
        // 4. 保存修改
        try {
            File file = new File(xmlPath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            XMLWriter out = new XMLWriter(new FileWriter(file));
            out.write(doc);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
