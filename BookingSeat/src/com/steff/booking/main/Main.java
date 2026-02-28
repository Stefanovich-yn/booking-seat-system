package com.steff.booking.main;

import java.util.Scanner;

public class Main {
	private static final char FREE_SYMBOL = 'F';
	private static final char BOOKED_SYMBOL = 'B';

	private static final int DEFAULT_ROWS = 5;
	private static final int DEFAULT_SEATS = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int m = DEFAULT_ROWS;
		int n = DEFAULT_SEATS;
		char[][] hall = new char[m][n];
		initHall(hall);

		while (true) {
			System.out.println("\n--- BOOKING MENU ---");
			System.out.println("1. Display hall");
			System.out.println("2. Reserve a seat");
			System.out.println("3. Cancel reservation");
			System.out.println("4. Show statistics");
			System.out.println("0. Exit");

			int choice = scanner.nextInt();

			int row;
			int seat;

			switch (choice) {
			case 1:
				displayHall(hall);
				break;
			case 2:
				System.out.println("Enter row:");
				row = scanner.nextInt();

				System.out.println("Enter seat:");
				seat = scanner.nextInt();

				bookSeat(hall, row, seat);
				break;
			case 3:
				System.out.println("Enter row:");
				row = scanner.nextInt();

				System.out.println("Enter seat:");
				seat = scanner.nextInt();

				cancelBooking(hall, row, seat);
				break;
			case 4:
				printStatistics(hall);
				break;
			case 0:
				scanner.close();
				return;
			default:
				System.out.println("Invalid option. Please try again");
				break;
			}
		}
	}

	public static void initHall(char[][] hall) {
		for (int i = 0; i < hall.length; i++) {
			for (int j = 0; j < hall[i].length; j++) {
				hall[i][j] = FREE_SYMBOL;
			}
		}

	}

	public static void displayHall(char[][] hall) {
		System.out.print("    ");
		for (int j = 1; j <= hall[0].length; j++) {
			System.out.print(j + " ");
		}
		System.out.println();

		for (int i = 0; i < hall.length; i++) {
			System.out.print((i + 1) + " | ");
			for (int j = 0; j < hall[i].length; j++) {
				System.out.print(hall[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void bookSeat(char[][] hall, int row, int seat) {
		int rowIndex = row - 1;
		int seatIndex = seat - 1;

		if (rowIndex < 0 || rowIndex >= hall.length || seatIndex < 0 || seatIndex >= hall[0].length) {
			System.out.println("Error! This seat doesn't exist!");
			return;
		}

		if (hall[rowIndex][seatIndex] == FREE_SYMBOL) {
			hall[rowIndex][seatIndex] = BOOKED_SYMBOL;
			System.out.println("Your seat has been successfully reserved!");
		} else {
			System.out.println("Sorry, but the seat is already reserved :(");
		}
	}

	public static void cancelBooking(char[][] hall, int row, int seat) {
		int rowIndex = row - 1;
		int seatIndex = seat - 1;

		if (rowIndex < 0 || rowIndex >= hall.length || seatIndex < 0 || seatIndex >= hall[0].length) {
			System.out.println("Error! This seat doesn't exist!");
			return;
		}

		if (hall[rowIndex][seatIndex] == BOOKED_SYMBOL) {
			hall[rowIndex][seatIndex] = FREE_SYMBOL;
			System.out.println("The booking has been successfully cancelled!");
		} else {
			System.out.println("Error! This seat is not reserved!");
		}
	}

	public static void printStatistics(char[][] hall) {
		int freeCount = 0;
		int bookedCount = 0;

		for (int i = 0; i < hall.length; i++) {
			for (int j = 0; j < hall[i].length; j++) {
				if (hall[i][j] == FREE_SYMBOL) {
					freeCount++;
				} else if (hall[i][j] == BOOKED_SYMBOL) {
					bookedCount++;
				}
			}
		}

		int totalSeat = freeCount + bookedCount;

		System.out.println("Total number of seats: " + totalSeat);
		System.out.println("Free seats: " + freeCount);
		System.out.println("Booked seats: " + bookedCount);
	}
}
