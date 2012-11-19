package backEnd;

import javax.swing.JLabel;

public class Timer implements Runnable{ 
	private long startTime;
	private long timeElapsed;
	private Thread runner;
	private boolean go;
	private transient final JLabel label;

	public long getTimeElapsed(){
		return timeElapsed;
	}

	public Timer(JLabel label){
		this.label = label;
		startTime = System.currentTimeMillis();
		runner = new Thread(this,"Timer");
		go = false;
		runner.start();
	}

	@Override
	public void run() {
		while(true){
			if(go){
				timeElapsed = System.currentTimeMillis() - startTime;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			label.setText(formatTime());
		}
	}

	private String formatTime() {
		final long temp = timeElapsed/1000;
		final String minutes = (temp/60)+":";
		final String seconds = (temp%60<10?"0":"")+(temp%60);
		return minutes+seconds;
	}

	public void start(){
		go = true;
	}

	public void reset(){
		startTime = System.currentTimeMillis();
	}

	public void stop(){
		go = false;
	}

	public boolean isRunning(){
		return go;
	}
}
