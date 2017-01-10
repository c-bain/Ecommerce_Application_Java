/*Name: Corie Bain, Piranaven Selvathayabaran, Darsikan Anandarajah
MacID: bainc2, selvatp, anandad
Student Number: 001436514, 001419766, 001226159
Description: E-commerce Application | Done with Bonus #1 and #2 included.*/
public abstract class Item { 
	public abstract String getInfo(int sNO); //getInfo class takes one parameter, sNo 
	public abstract int getPrice(int sNO, int quantity); //method getPrice takes the sNo and the quantity
	protected int price; //price variable stored as protected int.
	protected int sNO;  //sNo variable stored as protected int.
	protected int quantity; //quantity variable stored as protected int.
	protected String name; //name variable stored as protected int.
	protected String type;  //type variable stored as protected int.
 
}
