package password;

import java.awt.*;
import java.awt.datatransfer.*;

import javax.swing.*;

public class Timer implements Runnable {

	private int seconds;
	private Thread thread;
	private JTextArea textArea;
	private volatile boolean end = true;
	public Timer(JTextArea textArea ,String threadName) {
		seconds = 0;
		
		this.textArea = textArea;
		thread = new Thread(this, threadName);
		
		thread.start();
	}
	
	public void run() {
		
		while(end) {
			try {
				//System.out.println("Timer run , before sleep");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println("TImer run , after sleep");
			seconds++;
			if(seconds > 5) {
				if(end)
				textArea.setText("Copied Password will vanish in: " + (10-seconds) );	
			}
			if(seconds == 10) {
	            java.awt.datatransfer.Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
	            clpbrd.setContents(new StringSelection(""), null);
	            System.out.println("In Timer seconds");
	            textArea.setText("Welcome, " + Authorization.getCurrentUser().getUsername());
	            end  = false;
			}
		}
	}
	
	public synchronized void stop() {
		end = false;
	}
	
	public int getSeconds() {
		return this.seconds;
	}
	
	public Thread getThread() {
		return this.thread;
	}
	
	public JTextArea getTextArea() {
		return this.textArea;
	}
	
}
