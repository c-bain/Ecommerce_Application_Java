/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/

public class MP3 extends Audio { //This function returns the base price from the parent class.
	@Override 
	public int 	getPrice(int sNumber, int quantity){ //getPrice method takes a SNo and a price and overrides the previous method by using a new calculation based on certain criteria.
		String tempArray[] = getInfo(sNumber).split(","); //creates a temporary array and the elements are split by commas in order for easy access.
		int price =  Integer.parseInt(tempArray[3]); //since the split was used, the location of the price was known and it was stored in the variable price.
		price = price * quantity; //price is multiplied by quantity and the result is stored in the variable price.
		return price; //the price is returned.
	}

}
