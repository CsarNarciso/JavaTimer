import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Timer{
	
	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException, FileNotFoundException {
		
		String soundEffectPath = "soundEffect.wav";
		Scanner scanner = new Scanner(System.in);
		
		//Ask user for desired timer time
		System.out.print("Set timer at (in minutes): ");
		int endAt = scanner.nextInt();
		endAt = endAt * 60; //And pass to seconds for calcs
		
		long lastUpdateTimeInNanos = System.nanoTime();
		double totalElapsedSeconds = 0;
		
		//Init timer
		while(true){
		
			long currentTimeInNanos = System.nanoTime();
			
			double loopLapElapsedSeconds = (double) (currentTimeInNanos - lastUpdateTimeInNanos) / 1_000_000_000; //nano to second
			totalElapsedSeconds += loopLapElapsedSeconds; 
			
			//Display elapsed time
			int remainingHours = (int) ((endAt - totalElapsedSeconds) / 60 / 60);
			int remainingMinutes = (int) (((endAt - totalElapsedSeconds) - remainingHours * 60 * 60) / 60);
			int remainingSeconds = (int) ((endAt - totalElapsedSeconds) - (remainingHours * 60 * 60) - remainingMinutes * 60);
			System.out.println(remainingHours + "h | " + remainingMinutes + "m | " + remainingSeconds + "s");
			
			if(endAt - totalElapsedSeconds <= 0){					
				
				System.out.println("Finished! CTR-C to exit program, or start a new timer!");
				
				//Reset values for next timer
				totalElapsedSeconds = 0;
				
				//Play and repeat sound effect
				File file = new File(soundEffectPath);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
				while(true){
					clip.start();
				}
			}
			
			lastUpdateTimeInNanos = System.nanoTime();
			Thread.sleep(500);
		}
	}
}
