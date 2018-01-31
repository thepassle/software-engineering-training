import java.time.*;

public class SogyoQuotes {

    public static void main(String[] args){

        String[][] quotes = {
            {"galileo", "eppur si muove"},
            {"archimedes", "eureka!"},
            {"erasmus", "in regione caecorum rex est luscus"},
            {"socrates", "I know nothing except the fact of my ignorance"},
            {"rené descartes", "cogito, ergo sum"},
            {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
        };

        int day = LocalDate.now().getDayOfYear();
        int selectedQuote = day % quotes.length;

        // Split quote into chars so we can loop
        char[] chars = quotes[selectedQuote][1].toCharArray();
        
        for(int i = 0; i < chars.length; i++){
            
            // Capitalize char at index 0
            if(i == 0){
                System.out.print( Character.toString(chars[i]).toUpperCase());
            // Check if last letter is a '!' 
            } else if(i == chars.length-1 && chars[chars.length-1] != '!'){
                System.out.print(chars[i] + ".");
            } else {
                System.out.print(chars[i]);
            }
        }

        // Split name and capitalize each word
        String name = "";
        String[] nameArr = quotes[selectedQuote][0].split(" ");

        for(int i = 0; i < nameArr.length; i++){
            name += nameArr[i].substring(0, 1).toUpperCase() + nameArr[i].substring(1) + " ";
        }

        System.out.println( " -- " + name);
    }
}