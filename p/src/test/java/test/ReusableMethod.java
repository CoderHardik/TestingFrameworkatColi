package test;

import java.util.Scanner;

public class ReusableMethod {

	
	public static String Username() {
		Scanner s = new Scanner(System.in);
		String username;

		System.out.println("Enter login email address of user you want to test: ");
		username =s.nextLine();

		return username;
	}
	
	public static String Password() {
		Scanner s = new Scanner(System.in);
		String  password;

		System.out.println("Enter Password: ");
		password = s.nextLine();

		return password;
	}
	
}
