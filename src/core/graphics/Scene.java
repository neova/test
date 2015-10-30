package core.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

public class Scene {
		private static boolean created = false;
		public static Canvas content;
		//private static Dimension size;
			
		private static BufferedImage buffer;
		private static int[] bufferData;
		private static Graphics bufferGraphics;
		private static int clearColor = 0xffffffff;
//		public static JFrame window;
		
		private static BufferStrategy bufferStrategy;
			
		public static void create(JFrame win){
			if (created)
				return;
			content = new Canvas();
			content.setSize(win.getRootPane().getSize());
			win.getContentPane().add(content);
			//debagDialog.setVisible(true);
			created = true;
			content.setFocusable(false);
			buffer = new BufferedImage(win.getWidth(), 
					win.getHeight(), BufferedImage.TYPE_INT_ARGB);
			bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData(); 
			bufferGraphics = buffer.getGraphics();
			((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			//clearColor = _clearColor; 
			
			content.createBufferStrategy(2); 
			bufferStrategy = content.getBufferStrategy();
			
		}

		public static void clear(){
			Arrays.fill(bufferData, clearColor);
		}

		public static void swapBuffers(){
			try {
				try {
					Graphics g = bufferStrategy.getDrawGraphics();
					g.drawImage(buffer, 0, 0, null);
				} catch (IllegalStateException e) {
					// TODO: handle exception
				}
				
			} catch (NullPointerException e) {
				System.out.println("Чпок");
			}
			
			
			try {
				bufferStrategy.show();
			} catch (NullPointerException e) {
				System.out.println("Чпок");
			}
			
			
		}
		//Апофеоз гения
		public static Graphics2D getGraphics(){
			return (Graphics2D) bufferGraphics;
		}

		public static void destroy(){
			if (!created)
				return;

		}
		
		public static void resize(Dimension size){
			try {
				content.setSize(size);
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			buffer = new BufferedImage(content.getWidth(), 
					content.getHeight(), BufferedImage.TYPE_INT_ARGB);
			bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData(); 
			bufferGraphics = buffer.getGraphics();
			((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			//clearColor = _clearColor; 
			
			content.createBufferStrategy(2); 
			bufferStrategy = content.getBufferStrategy();
			
		}

		public static boolean isCreated() {
			// TODO Auto-generated method stub
			return created;
		}
		
//		public static void setTitle(String title){
//			window.setTitle(title);
//		}
		
//		public static void addInputListener(tInput inputListener){
//				window.add(inputListener);
//		}
		
//		public static int getWidth() {
//			return window.getWidth();
//		}
//		public static int getHeight() {
//			return window.getHeight();
//		}
}
