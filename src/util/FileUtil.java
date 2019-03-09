package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * �������ڴ�����Ŀ��Դ�Ĺ�����
 * <p>
 * Ҫע����ǣ���Ŀ��Դ����Ҫ�ŵ�����Ŀ¼src�£�Ҳ����Ӧ����Ŀ�ⲿ��Դ��ָ������·��
 */
public class FileUtil {
	//��Ŀ�ļ�����λ��srcĿ¼�µ�����3�����ļ���֮һ
	private static final String FILE = "file/";	//�����ͨ�ļ�
	private static final String IMAGE = "images/";	//���ͼƬ�ļ�
	private static final String AUDIO = "music/";	//�����Ƶ�ļ�
	private static final HashMap<String, String> MAP = new HashMap<String, String>();
	static {
		MAP.put("file", FILE);
		MAP.put("image", IMAGE);
		MAP.put("audio", AUDIO);
	}
	
	/**
	 * ������Դ�ļ���URL��ַ
	 * @param type ��Դ�ļ�����,�����ļ���ͼƬ����Ƶ
	 * @param path ��Դ�ļ�·�� �ⲿ�ļ�ʱҪ�þ���·�� (�� C:/me.jpg) ������ڲ��ļ������ļ�����(�磺me.jpg)
	 * @return ��Դ�ļ���URL��ַ
	 */
    public static URL getURL(String type, String path) {
    	String dir = MAP.get(type);
    	return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }
    
    //��ȡ�ļ���Դ
    public static File getFile(String path) {
    	URL url = getURL("file", path);
    	if (url == null) {
    		return null;
    	}
    	return new File(url.getFile());
    }
    
    //��ȡͼƬ��Դ
    public static Image getImage(String path) {
    	URL url = getURL("image", path);
    	if(url == null) {
    		return null;
    	}
    	return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    //��ȡ��Ƶ��Դ
    public static AudioClip getAudio(String path) {
    	URL url = getURL("audio", path);
    	if(url == null) {
    		return null;
    	}
    	return Applet.newAudioClip(url);
    }
    
}
