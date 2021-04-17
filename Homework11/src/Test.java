/*
 * Author: Zane Hassan
 * Date: 4/9/2021
 * The code is inteneded to read serialized data from a file and allow the user to manipulate it then write the data to a file.
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Person implements Serializable { // class for what data a person has
	String name ;
	int phone;
	int dob;
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public int getDob() {
		return dob;
	}


	public void setDob(int dob) {
		this.dob = dob;
	}


	public String geteAdress() {
		return eAdress;
	}


	public void seteAdress(String eAdress) {
		this.eAdress = eAdress;
	}


	String eAdress;
	@Override
	public String toString() {
		return "Person [name=" + name + ", phone=" + phone + ", dob=" + dob + ", eAdress=" + eAdress + "]";
	}
	
	
	public Person(String name, int phone, int dob, String eAdress) {
		super();
		this.name = name;
		this.phone = phone;
		this.dob = dob;
		this.eAdress = eAdress;
	}

	
	
}
public class Test  {

	public static void main(String[] args) {
		
		Scanner selectionScanner = new Scanner(System.in);
		String name;
		Scanner stringScanner = new Scanner(System.in); 
		Scanner numbScanner = new Scanner(System.in);
		int testNumb;
		
		int selection = 0;
		
		ArrayList<Person> people = new ArrayList<>();
		
		
		
		
		
		try { // read the data on the file
			readFile(people);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		while(selection != 5) {
	
			// this is the menu
			System.out.println("This is the person registery \n please enter a number to indicate what you would like to do:\n 1) Add information into a file.\r\n" + 
					" 2) Retrieve information from a file and display them.\r\n" + 
					" 3) Delete information.\r\n" + 
					" 4) Update information.\r\n" + 
					" 5) Exit.\r\n" + 
					"");
			selection = selectionScanner.nextInt();
			
			if (selection == 1){
				addInfo(people); // adds people to the array
			}
			else if (selection == 2){ // gets the data and displays it
				System.out.println("please enter the name of the person exactly as it was entered");
				name = stringScanner.nextLine();
				testNumb = getInfo(people, name);
				if (testNumb >= 0) {
					System.out.println(people.get(testNumb).toString());
				}
				else {
					System.out.println("Sorry we didn't find that name");
				}
			}
			else if (selection == 3){ // deletes info from array
				System.out.println("please enter the name of the person exactly as it was entered");
				name = stringScanner.nextLine();
				testNumb = getInfo(people, name);
				if (testNumb >= 0) {
					System.out.println("Deleting: " + people.get(testNumb).toString());
					people.remove(testNumb);
					
				}
				else {
					System.out.println("Sorry we didn't find that name");
				}
			}
			else if (selection == 4){ // changes data in array
				System.out.println("please enter the name of the person exactly as it was entered");
				name = stringScanner.nextLine();
				testNumb = getInfo(people, name);
				if (testNumb >= 0) {
					update(testNumb, people);
					
				}
				else {
					System.out.println("Sorry we didn't find that name");
				
				}
			}
			else if( selection == 5){ // updates the file and ends the program
				System.out.println("Ok we will update the file now!");
				try {
					writeToFile(people);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Sorry that is not a valid option");
			}
			
		}
		
		
			
		
		
			
			stringScanner.close();
			numbScanner.close();
			selectionScanner.close();

	}
	
	
	
	
	public static void update(int name, ArrayList<Person> array) { // updates info in array
		Scanner numbScanner = new Scanner(System.in);
		Scanner stringScanner = new Scanner(System.in);
		String uInput;
		int selection;
		System.out.println("What do you want to change? Please enter a number \\n 1) Name.\\r\\n\" + \r\n" + 
				"				\" 2) Phone Number.\\r\\n\" + \r\n" + 
				"				\" 3) Date of Birth.\\r\\n\" + \r\n" + 
				"				\" 4) Email.\\r\\n\" + \r\n" + 
				"				\"\");");
		selection = numbScanner.nextInt();
		if(selection == 1) { // finds what data they want to update and updates it
			System.out.println("Please enter what you want the name to be?");
			uInput = stringScanner.nextLine();
			array.get(name).setName(uInput);
		}else if(selection == 2) {
			System.out.println("Please enter what you want the Phone Number to be without any symbols or spaces in the form: 15555555555?");
			selection = numbScanner.nextInt();
			array.get(name).setPhone(selection);
		}else if(selection == 3) {
			System.out.println("Please enter what you want the Date of Birth to be without any symbols or spaces and always with two digets in the form: 01012000?");
			selection = numbScanner.nextInt();
			array.get(name).setPhone(selection);
		}else if(selection == 1) {
			System.out.println("Please enter what you want the email to be?");
			uInput = stringScanner.nextLine();
			array.get(name).setName(uInput);
		}
		
		stringScanner.close();
		numbScanner.close();
		
		
		
	}
	
	public static void addInfo(ArrayList<Person> array) { // adds person to list
		String name ;
		int phone;
		int dob;
		String eAddress;
		Scanner nameScanner = new Scanner(System.in) ;
		Scanner phoneScanner = new Scanner(System.in);
		/*Scanner dobScanner;
		Scanner eAdressScanner;*/
		
		System.out.println("Ok please enter the name of the person"); // collets data about person being added
		name = nameScanner.nextLine();
		System.out.println("Ok please enter the phone number of the person without any symbols or spaces in the form: 15555555555");
		phone = phoneScanner.nextInt();
		System.out.println("Ok please enter the date of birth of the person without any symbols or spaces and always with two digets in the form: 01012000");
		dob = phoneScanner.nextInt();
		System.out.println("Ok please enter the email of the person in the form: example@example.example");
		eAddress = nameScanner.nextLine();
		
		array.add(new Person (name, phone, dob, eAddress));
		
		nameScanner.close();
		phoneScanner.close();
		
	}
	
	public static int getInfo(ArrayList<Person> array, String name){ // gets a person from array
		int i =0;
		while (i < array.size()) {
			if( name == array.get(i).getName()) {
				return i;
			}
			i++;
			
		}
		return -1;
	}
	
	
	
	public static void writeToFile(ArrayList<Person> array) throws IOException { // to update file from array
		int i = 0;
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Person.bin"));
		
		while (i < array.size()) {
			objectOutputStream.writeObject(array.get(i));
			i++;
		}
		objectOutputStream.close();
	}
	public static void readFile(ArrayList<Person> array) throws IOException, ClassNotFoundException { // to add data to array from file
		int end;
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Person.bin"));
		while ((end = objectInputStream.read()) != -1) {
			Person name = (Person) objectInputStream.readObject();
			array.add(name);
		}
		
		objectInputStream.close();
	}
	
	

}
