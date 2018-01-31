import java.util.*;
import java.text.*;

public class SogyoClockGoed {
    public static void main(String[] args){
	    String timeConstruction = "";
	    int scale = 1;

	    // Get parameters from console
    	for(int i = 0; i < args.length; i++){
    		if(args[i].equals("-s")){
    			
    			int value = Integer.parseInt(args[i+1]);

    			if(value < 1 || value > 5){
			        throw new IllegalArgumentException("Pick a size between 1 and 5.");
    			} else {
	    			System.out.println("Size: " + value);
	    			scale = value;
	    		}
    		}

    		if(args[i].equals("-12") || args[i].equals("-24")){
	    		timeConstruction = args[i];
    		} else {
    			timeConstruction = "-24";
    		}
    	}

		System.out.println(timeConstruction);

	    String[] A = {
	      " - ", 
	      "| |", 
	      " - ", 
	      "| |", 
	      "   "
	    };

	    String[] P = {
	      " - ", 
	      "| |", 
	      " - ", 
	      "|  ", 
	      "   "
	    };

	    String[] zero = {
	      " - ", 
	      "| |", 
	      "   ", 
	      "| |", 
	      " - "
	    };

	    String[] one = {
	      "   ", 
	      "  |", 
	      "   ", 
	      "  |", 
	      "   "
	    };

	    String[] two = {
	      " - ", 
	      "  |", 
	      " - ", 
	      "|  ", 
	      " - "
	    };

	    String[] three = {
	      " - ", 
	      "  |", 
	      " - ", 
	      "  |", 
	      " - "
	    };

	    String[] four = {
	      "   ", 
	      "| |", 
	      " - ", 
	      "  |", 
	      "   "
	    };

	    String[] five = {
	      " - ", 
	      "|  ", 
	      " - ", 
	      "  |", 
	      " - "
	    };

	    String[] six = {
	      " - ", 
	      "|  ", 
	      " - ", 
	      "| |", 
	      " - "
	    };

	    String[] seven = {
	      " - ", 
	      "  |", 
	      "   ", 
	      "  |", 
	      "   "
	    };

	    String[] eight = {
	      " - ", 
	      "| |", 
	      " - ", 
	      "| |", 
	      " - "
	    };

	    String[] nine = {
	      " - ", 
	      "| |", 
	      " - ", 
	      "  |", 
	      " - "
	    };

	    // Set up date etc, check for AM/PM
		boolean am = false;
		String time = "";
		char[] timeArr = new char[4];

        Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("hhmm");

	    // Mn 12/24 uurs tijdsnotering klopt nog niet, die is omgedraaid, moet ik nog fixen
        if(timeConstruction.equals("-12")){
			ft = new SimpleDateFormat ("HHmm a");
	        time = ft.format(dNow);
	        
	        String[] timeSplit = time.split(" ");
	        
	        // check if am or pm
	        if(timeSplit[1].equals("AM")){
	        	am = true;
	        }

	        timeArr = timeSplit[0].toCharArray();
		} else {
	        time = ft.format(dNow);
	        timeArr = time.toCharArray();
		}

	/**	De timeArr is een array met de characters van de tijd, dus als het 12:51 is, is het {'1','2','5','1'};
		Eigenlijk is het ook makkelijker om een List<List<String>> te maken, want dan kan je makkelijker
		de A/P toevoegen als dat nodig is.

		Ik heb ook de colon tussen de cijfers niet toegevoegd, maar dat zou je met een List<List<String>> dus
		ook makkelijker kunnen doen */

        String[][] arrays = new String[4][];

        // voeg het corresponderende getal toe aan de array
        for(int i = 0; i < timeArr.length; i++){
          switch(timeArr[i]){
            case '0':
              arrays[i] = zero;
              break;
            case '1':
              arrays[i] = one;
              break;
            case '2':
              arrays[i] = two;
              break;
            case '3':
              arrays[i] = three;
              break;
            case '4':
              arrays[i] = four;
              break;
            case '5':
              arrays[i] = five;
              break;
            case '6':
              arrays[i] = six;
              break;
            case '7':
              arrays[i] = seven;
              break;
            case '8':
              arrays[i] = eight;
              break;
            case '9':
              arrays[i] = nine;
              break;
          }
        }

        // Initialize een array voor de formattering, maakt het makkelijker om zometeen te printen
	    String[][] arraysFormatted = new String[5][4];


	    // loop door de tijd array heen, dus als voorbeeld: 12:51
	    for(int j = 0; j < arrays[0].length; j++){
	    	// arrays[0].length is hier dus '1', dan '2', dan '5', dan '1'
	        for(int i = 0; i < arrays.length; i++){
	        	// loop dan door de visuele representatie van dat cijfer, dus voor '1' is dat
		        // String[] one ={
		        //                   "   ", 
		        //                 	 "  |", 
		        //	                 "   ", 
		        //                 	 "  |", 
		        //                 	 "   " 
		        //                          };

	        	// zet de visuele representatie om in een charArray, want we moeten extra dashes/pipes/spaties toevoegen
	        	// op basis van de scale(/size)
	            char[] charArray = arrays[i][j].toCharArray();
	            String dash = "";
	            String pipe = "";

	            // het element voor de tweede iteratie van het getal '1' is:
	            // {' ', ' ','|'}
	            // space, space, pipe

	            // als het element een dash bevat, moeten we extra dashes toevoegen
	            if(arrays[i][j].contains("-")){
					for(int k = 0; k < charArray.length; k++){

						// een dash is altijd alleen maar in de middelste 'tile' van de originele charArray
						if(k == 1){
							// herhaal het aantal dashes gebaseerd op de scale(/size)
							for(int l = 0; l < scale; l++){
								dash += charArray[k];
							}
						} else {
							dash += charArray[k];
						}
					}

					// voeg spaties toe
                    for(int l = 0; l < scale-1; l++){
	                    dash += " ";
                    } 
                    // voeg de dash toe aan de geformatteerde array, dat maakt het makkelijker om hem te printen
				    arraysFormatted[j][i] = dash;

	            // voor pipes doe je eigenlijk hetzelfde, maar bij een pipe moet je spaties in het midden toevoegen
				// omdat er in het midden nooit een pipe kan zijn, dus kan je daar rustig spaties toevoegen
	            } else {
	                for(int k = 0; k < charArray.length; k++){
	                    if(k == 1){
		                    for(int l = 0; l < scale-1; l++){
			                    pipe += " ";
		                    } 
	                    }
	                    pipe += charArray[k];
	                }

                    for(int l = 0; l < scale-1; l++){
	                    pipe += " ";
                    }
			    	arraysFormatted[j][i] = pipe;
	            }
	        }
		}


		// dan loop je door de geformatteerde array heen, en hoef je hem eigenlijk alleen nog maar te printen
		for(int i = 0; i < arraysFormatted.length; i++){
			if(i % 2 != 0){
				/** Hier print je de verticale rijen, de dashes zijn al verbreed, maar de pipes nog niet
				    dus dat gebeurt hier. De rij i % 2 != 0 bevat altijd een pipe namelijk */
				for(int k = 0; k < scale; k++){
					for(int j = 0; j < arraysFormatted[i].length; j++){
						System.out.print(arraysFormatted[i][j]);
					}
					System.out.println();
				}
			} else {
				/** Als het de rij met dashes is hoeven we die alleen te printen, omdat er al extra dashes
					toegevoegd zijn (op basis van de scale/size) */		
				for(int j = 0; j < arraysFormatted[i].length; j++){
					System.out.print(arraysFormatted[i][j]);
				}
				System.out.println();
			}
		}
    }
}

/** De oorspronkelijke array zag er zo uit, (uitgaand van de tijd 12:51)

String[][] arrays = {
	{ "   ", "  |", "   ", "  |", "   " }, // 1
	{ " - ", "  |", " - ", "|  ", " - " }, // 2
	{ " - ", "|  ", " - ", "  |", " - " }, // 5
	{ "   ", "  |", "   ", "  |", "   " }, // 1
};

Dus bevat 4 elementen, en elk element heeft 5 sub elementen
Dat is vet moeilijk om te printen, dus heb ik het omgedraaid naar 
een array met 5 elementen, met 4 sub elementen, namelijk:

String[][] arraysFormatted = {
       1      2      5      1
    {"   ", " - ", " - ", "   " },
    {"  |", "  |", "|  ", "  |" },
    {"   ", " - ", " - ", "   " },
    {"  |", "|  ", "  |", "  |" },
    {"   ", " - ", " - ", "   " }
}

*/
