import java.util.Arrays;
import java.util.Scanner;

public class Reservation {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean[][] small = new boolean[10][10];
        boolean[][] medium = new boolean[6][10];
        boolean[][] large = new boolean[4][10];

        /*
        Period 0 = 17.00 - 17.30
        Period 1 = 17.30 - 18.00
        Period 2 = 18.00 - 18.30
        Period 3 = 18.30 - 19.00
        Period 4 = 19.00 - 19.30
        Period 5 = 19.30 - 20.00
        Period 6 = 20.00 - 20.30
        Period 7 = 20.30 - 21.00
        Period 8 = 21.00 - 21.30
        Period 9 = 21.30 - 22.00
        */

       
        System.out.println("Input number and time ");
        for(int i = 0; i < 20; i++) {
            int clientNumber = sc.nextInt();
            int timeInHour = sc.nextInt();
            int timeInMin = sc.nextInt();

            System.out.println("Client #" + (i + 1));

            int period = ((timeInHour - 17) * 2) + (timeInMin / 30);

            if((clientNumber < 1 || clientNumber > 4) || (period > 8 || period < 0)) {
                System.out.println("Invalid, please try again!");
                continue;
            }
            if (clientNumber <= 1 && reserve(small, timeInHour, timeInMin, period)) {
                continue;
            }
            if (clientNumber <= 2 && reserve(medium, timeInHour, timeInMin, period)) {
                continue;
            }
            if (clientNumber <= 4 && reserve(large, timeInHour, timeInMin, period)) {
                continue;
            }
            
            System.out.println("Cannot reserve, please try again later!");
        }

        /*
        for(int i = 0; i < small.length; i++) System.out.println("Small #" + i + " " + Arrays.toString(small[i]));
        for(int i = 0; i < medium.length; i++) System.out.println("Medium #" + i + " " + Arrays.toString(medium[i]));
        for(int i = 0; i < large.length; i++) System.out.println("Large #" + i + " " + Arrays.toString(large[i]));
        */
    }

    static boolean reserve(boolean[][] type, int timeInHour, int timeInMin, int period) {
        int typeSize = type.length;
        for(int j = 0; j < typeSize; j++) {
            int endTimeInHour = 0;
            int endTimeInMin = 0;
            boolean isAvaliable = false;
            if(period < 7) {
                isAvaliable = !type[j][period] && !type[j][period + 1] && !type[j][period + 2] && !type[j][period + 3];
                if(isAvaliable) {
                    type[j][period] = type[j][period + 1] = type[j][period + 2] = type[j][period + 3] = true;
                }
                endTimeInHour = timeInHour + 2;
                endTimeInMin = timeInMin;
            }
            else if (period < 30) {
                isAvaliable = !type[j][period] && !type[j][period + 1] && !type[j][period + 2];
                if(isAvaliable) {
                    type[j][period] = type[j][period + 1] = type[j][period + 2] = true;
                }
                endTimeInHour = (timeInMin == 30) ? timeInHour + 2 : timeInHour + 1;
                endTimeInMin = 0;
            }
            else if (period < 9) {
                isAvaliable = !type[j][period] && !type[j][period + 1];
                if(isAvaliable) {
                    type[j][period] = type[j][period + 1] = true;
                }
                endTimeInHour = timeInHour + 1;
                endTimeInMin = timeInMin;
            }
            if(isAvaliable) {
                System.out.println("Start Time >> " + timeInHour + ":" + (timeInMin == 0 ? "00" : timeInMin));
                System.out.println("End Time >> " + endTimeInHour + ":" + (endTimeInMin == 0 ? "00" : endTimeInMin));
                return true;
            }
        }
        return false;
    }
}