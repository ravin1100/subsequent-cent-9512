package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Bill;
import com.masai.entities.Consumer;
import com.masai.entities.Transaction;
import com.masai.utilities.FileExists;

public class Main {
	public static void main(String[] args) {
//		checking file
		Map<Integer, Bill> bills = FileExists.billFile();
		Map<String, Consumer> consumers = FileExists.consumerFile();
		List<Transaction> transactions = FileExists.transactionFile();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome, in Electricity Bill Payment System");
		try {
			int preference=0;
			do {
				System.out.println("Please enter your preference: /n'1'-->Admin Login,/n'2'-->Consumer Login"
						+ " /n'3'-->Customer Signup /n'0'-->Exit");
				preference=sc.nextInt();
				switch(preference) {
				case 1: 
					adminFunctionality(sc,bills,consumers,transactions);
				break;
				case 2:
					consumerFunctionality(sc,consumers,bills,transactions);
					break;
				case 3:
					consumerSignUp(sc,consumers);
					break;
				case 0:
				System.out.println("You are successfully exited from the system");
					break;
				default:
						throw new IllegalArgumentException("Invalid Selection");
				}
			}while(preference!=0);	
		}
	catch(Exception e){
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
		finally{
//			this block of code will always be executed
		try {
			ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Bill.ser"));
			boos.writeObject(bills);
			ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Consumer.ser"));
			coos.writeObject("consumers");
			ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transaction.ser"));
			toos.writeObject("transactions");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
