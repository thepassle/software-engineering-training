import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class SogyoProfiling {
    public static void main(String[] args) throws Exception {

        File file = new File("profiling-data.txt");
        Scanner sc = new Scanner(file);

		ArrayList<Profile> profilesList = new ArrayList<Profile>();

        while(sc.hasNextLine()){
        	String[] lines = sc.nextLine().split(",");

        	// format name nicely, removes double space
        	String[] splitName = lines[0].split("  ");
        	String name = String.join(" ", splitName);

        	String[] bookList = new String[lines.length-1];

        	// save this users books in their bookList
			for (int i = 1; i < lines.length; i++) {
			   bookList[i-1] = lines[i].trim();
			}

			profilesList.add(new Profile(name, bookList));
        }


        // Het is wel mooi als het losse methods zijn eigenlijk
        Scanner ss = new Scanner(System.in);
	    System.out.println("Enter your Name: ");
	    String name = ss.nextLine().toLowerCase();

        for(Profile profile:profilesList){
        	if(profile.returnName().toLowerCase().contains(name)){
        		System.out.println("You are: " + profile.returnName() + ".\nYou have already read these books:");
        		for(int i = 0; i < profile.returnBooks().length; i++){
        			if(i == profile.returnBooks().length-1){
        				System.out.print(profile.returnBooks()[i] + ".\n");
        			} else {
	        			System.out.print(profile.returnBooks()[i] + ", ");    				
        			}
        		}
        	}
        }

	    System.out.println("Tell me a book title: ");
	    String booktitle = ss.nextLine().toLowerCase();
	    System.out.println("The following people have also read: " + booktitle.toUpperCase());

        for(Profile profile:profilesList){
        	for(int i = 0; i < profile.returnBooks().length; i++){
        		if(profile.returnBooks()[i].toLowerCase().contains(booktitle)){
        			System.out.println(profile.returnName());
        		}
        	}
        }


	    System.out.println("Give me your name again: ");
	    String name2 = ss.nextLine().toLowerCase();
	    String nameFound = "";
	    boolean personFound = false;
	    ArrayList<String> personsBooks = new ArrayList<String>();

	    // zoek persoon, kijk of ie uberhaupt gevonden is, zoja; pak zn boeken, en zn naam
        for(Profile profile:profilesList){
        	if(profile.returnName().toLowerCase().contains(name)){
        		personFound = true;
        		nameFound = profile.returnName();

        		for(int i = 0; i < profile.returnBooks().length; i++){
        			personsBooks.add(profile.returnBooks()[i]);
        		}
        	}
        }

        if(personFound){
		    ArrayList<String> recommendations = new ArrayList<String>();

	        for(Profile profile:profilesList){
	        	int matches = 0;
	        	if(profile.returnName().toLowerCase().contains(nameFound)){
	        		continue;
	        	} else {
	        		for(int i = 0; i < profile.returnBooks().length; i++){
	        			for(int j = 0; j < personsBooks.size(); j++){
	        				if(profile.returnBooks()[i].toLowerCase().equals(personsBooks.get(j).toLowerCase())){
	        					matches += 1;
	        				}
	        			}
	        		}

	        		if(matches >= 2){
	        			// als het een match is, loop nog een keer, en sla de boeken op die NIET overeenkomen met de personsBooks
		        		for(int i = 0; i < profile.returnBooks().length; i++){
		        			//Kijk of dat boek in de personsBooks zit.
		        			for(int j = 0; j < personsBooks.size(); j++){
		        				if(profile.returnBooks()[i].toLowerCase().equals(personsBooks.get(j).toLowerCase())){
		        					continue;
		        				} else {
		        					recommendations.add(profile.returnBooks()[i]);
		        					break;
		        				}
		        			}
		        		}
	        		}
	        	}
	        }
	        Random rn = new Random();
	        System.out.println( "You might look this book too: " + recommendations.get( rn.nextInt(recommendations.size()) + 1 ) );
        } else {
        	System.out.println("Sorry, we didnt find that person.");
        }
    }
}

class Profile {
	public String name;
	public String[] books;

	Profile(String _name, String[] _books){
		name = _name;
		books = _books;
	}

	// Dit is niet echt nodig
	public String returnName(){
		return name;
	}

	public String[] returnBooks(){
		return books;
	}
}