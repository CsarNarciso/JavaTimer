import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Timer{
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		
		//Ask user for desired timer time
		System.out.print("Set timer at (in minutes): ");
		int endAt = scanner.nextInt();
		
		//And pass to seconds for calcs
		endAt = endAt * 60;
		
		long lastUpdateTimeInNanos = System.nanoTime();
		
		double totalElapsedSeconds = 0;
		
		while(true){
			
			long currentTimeInNanos = System.nanoTime();
			
			double loopLapElapsedSeconds = (double) (currentTimeInNanos - lastUpdateTimeInNanos) / 1_000_000_000; //nano to second
			totalElapsedSeconds += loopLapElapsedSeconds; 
			
			//Display elapsed time
			System.out.println((int)totalElapsedSeconds);
			
			if(totalElapsedSeconds >= endAt){					
				System.out.println("Finished!");
				break;
			}
			
			lastUpdateTimeInNanos = System.nanoTime();
			
			Thread.sleep(500);
		}
	}
}