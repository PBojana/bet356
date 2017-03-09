package bet356.bet356;

public class Wait {

	 
		public static void seconds(int second){
			long time1;
		    long time0 = System.currentTimeMillis();

		    do {
		        time1=System.currentTimeMillis();
		    }
		    while (time1-time0<(second * 1000));
		    }
		}