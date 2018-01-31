import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.stream.Stream;   
import java.util.List;
import java.util.stream.Collectors;


public class SogyoLambda {
    public static void main(String[] args) throws Exception {

        File file = new File("profiling-data.txt");
        Scanner sc = new Scanner(file);

		List<Profile> profilesList = new ArrayList<Profile>();

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


        List<Profile> moreThanFiveBooks = profilesList.stream()
        	.filter(p -> p.books.length > 5)
			.collect(Collectors.toList());  

        List<Profile> readGreatExpectations = profilesList.stream()
        	.filter(p -> Arrays.asList(p.books).contains("Great Expectations") )
			.collect(Collectors.toList());  
        

		String[] test = new String[] {"a", "b", "C"};
		Arrays.stream(test).filter(book -> book.contains("a") ).forEach( b -> System.out.println(b));


        List<Profile> twoOrMoreWithThe = profilesList.stream()
        	.filter(p -> Arrays.stream(p.books)
        		.filter(b -> b.contains("The")) 
        	)
			.collect(Collectors.toList()
		);  

        List<Profile> twoOrMoreWithThe = profilesList.stream()
        	.forEach( p -> Arrays.stream(p.books)
        		.filter( book -> book.contains("The") ).toList()
	    	).collect(Collectors.toList());


        // System.out.println("\n# These people have read > 5 books");
        // moreThanFiveBooks.forEach(  
        //         profile -> System.out.println("   -" + profile.name)   
        // ); 

        // System.out.println("\n# These people have read Great Expectations");
        // readGreatExpectations.forEach(  
        //         profile -> System.out.println("   -" + profile.name)   
        // ); 

        // System.out.println("\n# These people have read two or more books with 'The' in the name");
        // twoOrMoreWithThe.forEach(  
        //         profile -> System.out.println("   -" + profile.name)   
        // ); 

    }
}

class Profile {
	public String name;
	public String[] books;

	Profile(String _name, String[] _books){
		name = _name;
		books = _books;
	}

	public String returnName(){
		return name;
	}

	public String[] returnBooks(){
		return books;
	}
}