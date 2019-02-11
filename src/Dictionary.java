import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Dictionary {
  public Dictionary() {
    
  }
  public static void genPrimes(int n) {
		System.out.println("Starting Calculation...");
    
    FileWriter fstream = null;
    BufferedWriter out = null;
		int N = n;
		long startTime = System.nanoTime();
    int lineNumber = 0;
    int primes = 0;
    File file = new File("PrimeDictionary");
    boolean[] isPrime = new boolean[N + 1];
		
    for (int i = 2; i <= N; i++) {
			isPrime[i] = true;
		}

		for (int i = 2; i * i <= N; i++) {
			if (isPrime[i]) {
				for (int j = i; i * j <= N; j++) {
					isPrime[i * j] = false;
				}
			}
		}
		// count primes
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
      fstream = new FileWriter("PrimeDictionary", true);
      out = new BufferedWriter(fstream);
      Scanner in = new Scanner(new File("PrimeDictionary"));
			while (in.hasNext()) {
				in.nextLine();
				lineNumber++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		for (int i = 2; i <= N; i++) {
			if (isPrime[i] && primes > lineNumber) {
				try {
					out.write(Double.toString(i) + "\n");
				}
				catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
			if(isPrime[i]) {
        primes++;
			}
		}
    try {
      out.close();
    } catch (Exception e) {
      
    }
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		double seconds = (double) ((double) duration / 1000);

		System.out.println("The number of primes <= " + N + " is " + (primes-1+lineNumber));
		System.out.println("This calculation took about " + seconds + " seconds");
	}
  public static void getPrime(int NPrime) {
		try {
			Scanner in = new Scanner(new File("PrimeDictionary"));
			int i = 1;
			int flag = 0;
			double prime = 0;
			while (in.hasNext()) {
				if (i+1 == NPrime) {
					prime = Double.parseDouble(in.nextLine());
					flag = 1;
					System.out.println("The " + NPrime + "th prime is: " + prime);
					flag = 1;
					break;
				}
				in.nextLine();
				i++;
			}
			if (flag == 0) {
				System.out.println("Not enough primes generated");
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}