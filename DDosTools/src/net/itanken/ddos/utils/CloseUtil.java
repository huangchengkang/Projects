package net.itanken.ddos.utils;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.itanken.ddos.DDosFrame;;

public class CloseUtil {

	/**
	 * 关闭 JFrame 窗口
     * @param exit 是否退出程序
     * @param frame JFrame对象
     * @param msg 提示信息
	 */
	public static boolean exitFrame(boolean exit, JFrame frame, String msg) {
		Icon img = new ImageIcon(CloseUtil.class.getResource("../res/wen"));
        int result = JOptionPane.showConfirmDialog(frame, msg, "提示", 
        		JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
        if(result == JOptionPane.YES_OPTION && exit) { 
        	System.exit(0);
            return true;
        } else if(result == JOptionPane.YES_OPTION && !exit) {
        	frame.setVisible(false); 
        	frame.setDefaultCloseOperation(2);
            return true;
        } else if(result == JOptionPane.NO_OPTION)  { 
            frame.setVisible(true); 
            frame.validate(); 
        }
        return false;
    } 

	/**
	 * 关闭 JDialog 窗口
	 * @param dialog 对话窗口
	 * @param msg 对话提示信息
	 */
	public static boolean exitDialog(JDialog dialog, Object msg) {
		Icon img = new ImageIcon(CloseUtil.class.getResource("../res/wen"));
        int result = JOptionPane.showConfirmDialog(dialog, msg,"提示",
        		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img);
        if(result == JOptionPane.YES_OPTION) { 
        	dialog.setVisible(false); 
        	dialog.setDefaultCloseOperation(2);
            return true;
        } else if(result == JOptionPane.NO_OPTION) { 
        	dialog.setVisible(true); 
        	dialog.validate(); 
        }
        return false;
    } 
	
	/**
	 * 关闭 JFrame / JDialog 窗口
     * @param exit 是否退出程序
	 * @param parentCom JFrame 或 JDialog
	 * @param showDialog 是否显示提示信息
	 * @param msg 提示信息
	 */
	public static boolean exit(boolean exit, Component parentCom, boolean showDialog, Object msg) {
		// DDosFrame.console.showLog("CloseUtil.exit() - Component名称：" + parentCom.getName());
        int result = 0;
        if(showDialog) {
        	Icon img = null;
			try {
				img = new ImageIcon(DDosFrame.class.getResource("res/wen"));
			} catch (Exception e) {
				img = null;
			}
        	result = JOptionPane.showConfirmDialog(parentCom, msg, "提示", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
        }
        if(result == JOptionPane.YES_OPTION) { 
        	if(parentCom.getName().toLowerCase().contains("frame")) { // 判断组件类型
        		if(exit) {
        			// DDosFrame.console.showLog("关闭JFrame（会退出程序）");
            		System.exit(0);
        		} else {
        			// DDosFrame.console.showLog("关闭JFrame（不退出程序）");
            		parentCom.setVisible(false);
            		((JFrame) parentCom).setDefaultCloseOperation(2); // DISPOSE_ON_CLOSE 隐藏并释放窗体
        		}
        	} else if(parentCom.getName().toLowerCase().contains("dialog")) {
        		// DDosFrame.console.showLog("关闭JDialog（不退出程序）");
        		parentCom.setVisible(false);
        		((JDialog) parentCom).setDefaultCloseOperation(2); // DISPOSE_ON_CLOSE 隐藏并释放窗体
        	} else {
        		System.exit(0);
        	}
            return true;
        } else if(result == JOptionPane.NO_OPTION) { 
        	parentCom.setVisible(true); 
        	parentCom.validate(); 
        }
        return false;
    }
}
