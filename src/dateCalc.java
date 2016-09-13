import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class dateCalc {
	public static void main(String[] args) throws ParseException {
		//BufferedReader br = null;

        //br = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Insert first date : ");
        Date dt1 = sdf.parse("2015-04-16");

        System.out.println("Insert second date : ");
        Date dt2 = sdf.parse("2015-12-08");

        long diff = dt2.getTime() - dt1.getTime();

        System.out.println("Days: " + diff / 1000L / 60L / 60L / 24L);

        String tmpDay = "2015-04-15";
        Date dayT = sdf.parse(tmpDay);
        int d=12012-(236)*4-1;
        for(int i=1; i<=(236)*4+8; i+=4)
        {
        	
        	dayT = new Date(dayT.getTime() + 1 * (24 * 3600000));
    		String result = sdf.format(dayT);
    		System.out.println(result);
    		System.out.println(d+i);
        }

	}
	
	public static int daysBetween(Calendar day1, Calendar day2){
	    Calendar dayOne = (Calendar) day1.clone(),
	            dayTwo = (Calendar) day2.clone();

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}
	
	static String getNextWeekDate(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// String dateString="2016-02-15";
		Date myDate = dateFormat.parse(dateString);
		Date oneDayBefore = new Date(myDate.getTime() + 1 * (24 * 3600000));
		String result = dateFormat.format(oneDayBefore);
		System.out.println(result);
		return result;
	}
}
