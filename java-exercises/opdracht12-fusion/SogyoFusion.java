import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/** Goedkoopste zoeken werkt, 'alleen' nog de tree printen
TO DO: 
	- Tree node/binary tree opzoeken
	- JSON voorbeeld?
*/
	
public class SogyoFusion {

    public static void main(String[] args) {
    	Data data = new Data();
		data.compare("Power I", 0);
	}
}

class BasicItem {
	public String name;
	public int price;
	public int itemLvl;

	BasicItem(String _name, int _price, int _itemLvl){
		name = _name;
		price = _price;
		itemLvl = _itemLvl;
	}
}

class FusedItem {
	public String name;
	public String[] requirements;
	public int totalPrice;
	public boolean isBuyable;

	FusedItem(String _name, String[] _requirements, int _totalPrice, boolean _isBuyable){
		name = _name;
		requirements = _requirements;
		totalPrice = _totalPrice;
		isBuyable = _isBuyable;
	}
}

class Data {
	public ArrayList<BasicItem> basicItems = new ArrayList<BasicItem>();
	public ArrayList<FusedItem> fusedItems = new ArrayList<FusedItem>();

	public void run(String input){
    	Data data = new Data();
    	ArrayList<BasicItem> basicItems = data.getBasicItems();
		ArrayList<FusedItem> fusedItems = data.getFusedItems();
	}

	public void compare(String input, int index){
		Data data = new Data();
		System.out.println();
		System.out.println();
		int basicPrice = data.getBasicPrice(input);

		/** Wat wel mooi zou zijn is een nieuw fusion object, dat de laagste fusion price
		heeft en de requirements en de naam van het item.
		
		class Fusion{
			String name;
			String[] requirements;
			int price;

			Fusion(String _name, String[] _requirements, int _price){
				name = _name;
				requirements = _requirements;
				price = _price;
			}

			methods etc
		}
	
		 */

		int lowestFusionPrice = data.getLowestFusionPrice(input);
		String[] lowestFusionRequirements = data.getLowestFusionRequirements(input);

		boolean requirementsForSale = true;


		System.out.println("IN: "+input);
		System.out.println("    -Buy cost:  " +basicPrice);
		System.out.println("    -Fuse cost: "+lowestFusionPrice);
		System.out.println("    -To fuse you would need: ");
		for(String item : lowestFusionRequirements){
			System.out.println("        -"+item);
		}
		System.out.println();

		if(basicPrice > lowestFusionPrice && index < 10 || basicPrice == 0){
			System.out.println("You should FUSE the: " + input);
	   		for(String requirement : lowestFusionRequirements){
	   			index++;
	   			System.out.println("Index: " + index);
	   			data.compare(requirement, index);
	   		}
		} else {
			System.out.println("You should BUY the: " + input);
		}
	}

	public int getBasicPrice(String input){
		Data data = new Data();
    	ArrayList<BasicItem> basicItems = data.getBasicItems();

    	int basicPrice = 0;

    	for(int i = 0; i < basicItems.size(); i++){
    		if(basicItems.get(i).name.equals( input )){
    			basicPrice = basicItems.get(i).price;
    		}
    	}
		return basicPrice;
	}

	public int getLowestFusionPrice(String input){
		Data data = new Data();
		ArrayList<FusedItem> fusedItems = data.getFusedItems();

    	int lowestFusionPrice = 0;
    	int priceTemp = 0;

    	boolean bothForSale = true;

		ArrayList<Integer> prices = new ArrayList<Integer>();

    	for(int i = 0; i < fusedItems.size(); i++){
    		priceTemp = 0;
    		bothForSale = true;
    		if(fusedItems.get(i).name.equals(input)){
    			for(int j = 0; j < fusedItems.get(i).requirements.length; j++){
    				// dit zijn de reqs
    				// als 1 van deze 2 values 0 is
    				priceTemp += data.getBasicPrice( fusedItems.get(i).requirements[j] );
    				if(priceTemp == 0){
    					bothForSale = false;
    					break;
    				}
    			}
    			if(bothForSale){
	    			prices.add(priceTemp);
    			}
    		}
    	}

    	int min = prices.get(0);

		for(Integer i: prices) {
		    if(i < min) min = i;
		}

    	return min;
	}

	public String[] getLowestFusionRequirements(String input){
		Data data = new Data();
		ArrayList<FusedItem> fusedItems = data.getFusedItems();

    	int lowestFusionPrice = 0;
    	int priceTemp = 0;
    	int prevPrice = 0;

		String[] requirements = new String[2];

		int lowestPrice = data.getLowestFusionPrice(input);

		for(int i = 0; i < fusedItems.size(); i++){
			priceTemp = 0;
    		if(fusedItems.get(i).name.equals(input)){
    			for(int j = 0; j < fusedItems.get(i).requirements.length; j++){
    				priceTemp += data.getBasicPrice( fusedItems.get(i).requirements[j] );
    			}
    			if(priceTemp == lowestPrice){
    				requirements[0] = fusedItems.get(i).requirements[0];
    				requirements[1] = fusedItems.get(i).requirements[1];
    			}
    		}
		}

    	return requirements;
	}

	public ArrayList<FusedItem> getFusedItems() {
		File file;
		Scanner sc;
		ArrayList<String> lines = new ArrayList<String>();
    	Data data = new Data();
    	ArrayList<BasicItem> basicItems = data.getBasicItems();

		int parsingItemLvl = 1;

		boolean isBuyable = false;
		int totalPrice = 0;
	    try { 
	        sc = new Scanner(new File("item-fusion-data.txt"));
			while(sc.hasNextLine()) {
			    lines.add(sc.nextLine());
			}
			sc.close();
	    } catch (java.io.FileNotFoundException e) {
	    	System.out.println(e);
	    }

		for(int i = 0; i < lines.size(); i++){
			if(lines.get(i).contains("+") && lines.get(i).contains("=")){

				String[] requirements = new String[2];
				String name = "";
				isBuyable = false;
				totalPrice = 0;

				String[] splitLine = lines.get(i).split("\\+");
				requirements[0] = splitLine[0].trim();

				String[] splitLine2 = splitLine[1].split("=");
				requirements[1] = splitLine2[0].trim();

				for(int k = 0; k < requirements.length; k++){
					for(int l = 0; l < basicItems.size(); l++){
						if(requirements[k].equals( basicItems.get(l).name )){
							totalPrice += basicItems.get(l).price;
							isBuyable = true;
							break;
						} else {
							isBuyable = false;
						}
					}
				}

				name = splitLine2[1].trim();

		    	FusedItem fusedItem = new FusedItem(name, requirements, totalPrice, isBuyable);
		   		fusedItems.add(fusedItem);
			}
		}

		return fusedItems;
	}

	public ArrayList<BasicItem> getBasicItems() {
		File file;
		Scanner sc;
		ArrayList<String> lines = new ArrayList<String>();

		int parsingItemLvl = 1;

	    try { 
	        sc = new Scanner(new File("item-fusion-data.txt"));
			while(sc.hasNextLine()) {
			    lines.add(sc.nextLine());
			}
			sc.close();
	    } catch (java.io.FileNotFoundException e) {
	    	System.out.println(e);
	    }

		for(int i = 0; i < lines.size(); i++){
			if(parsingItemLvl == 6){
				break;
			}

			if(lines.get(i).contains("*****")){
				parsingItemLvl += 1;
			} 

			if(lines.get(i).contains("~") && !lines.get(i).contains("Name") && parsingItemLvl == 1){
				String line = lines.get(i).replace("*","");
				String[] splitLine = line.split("~");

		    	BasicItem basicItem = new BasicItem(splitLine[0].trim(), Integer.parseInt(splitLine[1].trim()), parsingItemLvl);
		   		basicItems.add(basicItem);
			}

			if(lines.get(i).length() > 0){

				if(lines.get(i).charAt(0) == '*' && parsingItemLvl >= 2 && lines.get(i).contains("~")){
					String line = lines.get(i).replace("*","");
					String[] splitLine = line.split("~");

			    	BasicItem basicItem = new BasicItem(splitLine[0].trim(), Integer.parseInt(splitLine[1].trim()), parsingItemLvl);
			   		basicItems.add(basicItem);
				}
			}
		}
		return basicItems;
	}
}