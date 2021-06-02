
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {
    // Date is stored as int [day, month, year, hour, minute, second, millisecond];
    
    /**
     * Launch Window code
     * To Do:
     *  - Calculate Start time by iteration
     */
    // Number of points
    public static int numPoints;
    
    // Point Time after launch
    public static String[] points;
    
    // Minimum date for launch
    public static String startDate;
    
    // List of light times; key=point#, value=list of lighting times
    public static Map<Integer, ArrayList<String>> pointLightTimes = new HashMap<>();
    
    // Start Launch Time
    public static String startLaunchTime;
    
    public static void main(String[] args) throws IOException, Exception{
        // Make files
        File LightTimes = new File("src/Lighting_Times.txt");
        File input = new File("src/Input.txt");
        input.createNewFile();
        LightTimes.createNewFile();
        
        // Make file readers
        FileReader frInput = new FileReader(input);
        FileReader frLightTimes = new FileReader(LightTimes);
        
        // Process Input
        try {
            // Process Input
            BufferedReader br = new BufferedReader(frInput);
            String line;
            int lineCount = 0;
            while((line = br.readLine()) != null) {
                // Num Points
                if(lineCount == 0) {
                    numPoints = processValue(line.charAt(0));
                    points = new String[numPoints];
                }
                
                // Points
                if(lineCount <= numPoints && lineCount > 0) {
                    points[lineCount-1] = line.substring(4);
                }
                
                // Last line: Start date
                if(lineCount==(2+numPoints)) {
                    startDate = line;
                }
                
                lineCount++;
            }
            
            // Map initialization
            for(int i=0; i<numPoints;i++) {
                pointLightTimes.put(i+1, new ArrayList<>());
            }
            br.close();
            
            // Process Lighting Times
            BufferedReader br2 = new BufferedReader(frLightTimes);
            String line2;
            int lineCount2 = 0;
            int pointOn = 1;
            while((line2 = br2.readLine()) != null) {
                // If still on point
                if(line2.length() != 0) {
                    pointLightTimes.get(pointOn).add(line2);
                } else {
                    // New Point info
                    pointOn++;
                }
                
                lineCount2++;
            }
            
        } catch(IOException e) {
            System.out.println(e);
        }
        
        //System.out.println(startDate);
        String a = addDate(startDate, startDate);
        // Get Start Time
        //getStartTime();
        
        
        
        
        //for(int i=0; i<numPoints; i++) {
//            for(String pointData: pointLightTimes.get(4)) {
//                System.out.println(pointData + " ");
//            }
//            System.out.println("New Point");
       // }
//        System.out.println(startDate);
        
        // Close file readers
        frInput.close();
        frLightTimes.close();
    }
    
    // Converting to ASCII format
    private static int processValue(char v) {
        return Integer.parseInt(Character.toString((char)v));
    }
    
    // Get Start time of Launch Window
    // 1000 iterations per second.
    private static String getStartTime() {
        boolean finished = false;
        String startLaunch = startDate;
        double iteration = 0.000;
        
        // First iteration, get start time of point
        String[] startTimes = new String[numPoints];
        String[] endTimes = new String[numPoints];
        for(int i=0; i<numPoints; i++) {
            String time = pointLightTimes.get(i+1).get(0);
            // Get Whitespace
            int j=0;
            while(true) {
                if(time.charAt(j) == ' ' && time.charAt(j+3) == ' ') {
                    // Start Time 
                    startTimes[i] = time.substring(0, j);
                    endTimes[i] = time.substring(j+4, time.length()-1);
                    break;
                } else {
                    j++;
                }
            }
        }
        
        for(String s : startTimes) {
            System.out.println(s);
        }
        for(String s : endTimes) {
            System.out.println(s);
        }

        while(finished == false) {
            // Keep iterating
            // Get start time of first point
            if(iteration == 0.000) {
                // First Iteration
                // Skip
            }
            
            // Subtract point time after launch start from start time to get launch time
            
            
            
            // Test if all points work
            
            // If they work, break; If not, continue iterating but 
            // add 1millisecond if next test time < end time.
            // else, go to next oppertunity.
            
            
            // Finished
            finished = true;
        }
       
        // Return start
        return startLaunch;
    }
    
    private static String getStartTime2() {
        double interval = 0.000; 
        String startLaunch = startDate;
        boolean done = false;
        while(!done) {
            String launchTime = startDate + interval;
        }
        
        return startLaunch;
    }
    
    private static String addDate(String date1, String date2) throws Exception {
        long time1 = 0, time2 = 0;
        int[] time1p = new int[3];
        int[] time2p = new int[3];
        int count1 = 0, count2 = 0;
        
        // Set values
        for(int i=0; i<date1.length(); i++) {
            if(date1.charAt(i) == ' ') {time1p[count1] = i; count1++;}
        }
        
        for(int i=0; i<date2.length(); i++) {
            if(date2.charAt(i) == ' ') {time2p[count2] = i; count2++;}
        }
        
        // time1 calculation
        long day = Integer.parseInt(date1.substring(0, time1p[0])) * 86400000;
        long month = Integer.parseInt(date1.substring(time1p[0]+1, time1p[1]));
        long year = Integer.parseInt(date1.substring(time1p[1]+1, time1p[2]));
        long hour = Integer.parseInt(date1.substring(time1p[2]+1, time1p[2]+3));
        long minute = Integer.parseInt(date1.substring(time1p[2]+4, time1p[2]+6));
        long second = Integer.parseInt(date1.substring(time1p[2]+7, time1p[2]+9));
        long milli = Integer.parseInt(date1.substring(time1p[2]+10, time1p[2]+13));
        int days = getDays(month, year);
        month = days * 86400000;
        if(year%4==0) {year = 366 * 86400000;} else {year = 365 * 86400000;}
        hour = hour * 3600000;
        minute = minute * 60000;
        second = second * 1000;
        time1 = day + month + year;
        time1 = month * time1;
        time1 = year * time1;
        
        System.out.println(milli);
        return null;
    }
    
    private static int getDays(long month, long year) {
        switch((int)month) {
            case 1:
                return 31;
            case 3:
                return 31;
            case 5:
                return 31;
            case 7:
                return 31;
            case 8:
                return 31;
            case 10:
                return 31;
            case 12:
                return 31;

            case 4:
                return 30;
            case 6:
                return 30;
            case 9:
                return 30;
            case 11:
                return 30;

            case 2:
                if(year % 4 == 0) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 30;
    }
}
