class SogyoLeapYear {
	public static void main(String[] args){
	    int year = 306;
	    checkLeapYear(year);
    }

    public static void checkLeapYear(int year){
    	if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
    		System.out.println("The year is a leap year");
    	} else {
    		System.out.println("Nope");
    	}

   }	
}

