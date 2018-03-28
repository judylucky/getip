/**
 * 
 */
package com.enn.utils;

/**
 * @author lxp 2017年9月11日
 */
public class ContinueThread extends Thread {

	@Override
	public void run() {
		/*while (true) {*/
			System.out.println(this.getName() + " is running");
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}

	public static void main(String[] args) {
		ContinueThread ct = new ContinueThread();
		ct.start();
	}
}
