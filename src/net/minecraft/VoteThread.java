package net.minecraft;

public class VoteThread extends Thread {
	@Override
	public void run() {
		try {
			String parameters = "vote=14171";
			String result = Util.excutePost("http://mcsv.ru", parameters);

			if (result == null) {
			}
			return;
		} catch (Exception e) {
			return;
		}
	}
}
