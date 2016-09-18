package com.sumwinsun.common.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ConfigUtils {
	private static Logger log = Logger.getLogger(ConfigUtils.class);
	//发布时要调整该路径
	private static final String classpath = ConfigUtils.class.getClassLoader().getResource("").getPath();
	/**
	 * 解析properties文件
	 * @param fileName
	 * @return
	 */
	public static Map resolveConfigPropFile(String fileName){
		InputStream is = null;
		Map map = new HashMap();
		try {
			is = new FileInputStream(classpath+"/config/"+fileName);
			Properties prop = new Properties();
			try {
				prop.load(is);
				Enumeration keys = prop.propertyNames();//取出所有的key
				while(keys.hasMoreElements()){
					String elem = (String) keys.nextElement();
					map.put(new String(elem.getBytes("iso-8859-1"),"utf-8"), 
							new String(prop.getProperty(elem).getBytes("iso-8859-1"),"utf-8"));
				}
			} catch (IOException e) {
				log.info("加载配置文件："+fileName+"出错！"+e);
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			log.info(fileName+"文件未找到");
			e.printStackTrace();
		} finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					log.info("配置文件："+fileName+"不能关闭！"+e);
					e.printStackTrace();
				}
			}
		}
		return map;
		
	}
	
	public static String getClassPath(){
		return classpath;
	}
	
	/**
	 * 解析xml字符串
	 * @param xmlData
	 * @return
	public static Map<String, Object> xmlStringElements(String xmlData){
		  Map<String, Object> map = new HashMap<String, Object>();
		  Document doc = null;
		  try {
			  
			  //将字符串解析为XML
			  doc = DocumentHelper.parseText(xmlData);
			  //获取根节点
			  Element rootElm = doc.getRootElement();
			  //log.info("根节点："+rootElm.getName());
			  Iterator iter = rootElm.elementIterator();
			  while(iter.hasNext()){
				  Element childCode = (Element) iter.next();
				  System.out.println(childCode.getStringValue()+"*-*-**-**---**"+childCode.getName()+"++++"+childCode.getText());
				  map.put(childCode.getName(), childCode.getStringValue());
			  }
		  } catch (DocumentException e) {
			  e.printStackTrace();
		  }
		  return map;
	}
	
	*//**
	 * 解析xml文件
	 * @param path
	 * @return
	 *//*
	public static Map<String, Object> xmlFileElements(String path){
		Map<String, Object> map = new HashMap<String, Object>();
		//获得dom解析器工厂
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null;
		try {
			//获取具体的dom解析器
			db = dbfactory.newDocumentBuilder();
			try {
				Document doc = (Document) db.parse(new File(path));
				//获取根节点
				Element rootElm = doc.getRootElement();
				//log.info("根节点："+rootElm.getName());
				Iterator iter = rootElm.elementIterator();
				while(iter.hasNext()){
					 Element childCode = (Element) iter.next();
					 System.out.println(childCode.getStringValue()+"*-*-**-**---**"+childCode.getName()+"++++"+childCode.getText());
					 map.put(childCode.getName(), childCode.getStringValue());
				}
			} catch (SAXException e) {
				log.info("");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return map;
	}*/


}
