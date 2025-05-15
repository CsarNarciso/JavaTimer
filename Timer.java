import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Timer{
	
	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException, FileNotFoundException {
		
		Scanner scanner = new Scanner(System.in);
		
		//Ask user for desired timer time
		System.out.print("Set timer at (in minutes): ");
		int endAt = scanner.nextInt();
		
		//And pass to seconds for calcs
		endAt = endAt * 60;
		
		long lastUpdateTimeInNanos = System.nanoTime();
		
		double totalElapsedSeconds = 0;
		
		String soundEffectPath = "soundEffect.wav";
		
		while(true){
			
			long currentTimeInNanos = System.nanoTime();
			
			double loopLapElapsedSeconds = (double) (currentTimeInNanos - lastUpdateTimeInNanos) / 1_000_000_000; //nano to second
			totalElapsedSeconds += loopLapElapsedSeconds; 
			
			//Display elapsed time
			System.out.println((int)totalElapsedSeconds);
			
			if(totalElapsedSeconds >= endAt){					
				
				System.out.println("Finished! CTR-C to exit program.");
				
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
