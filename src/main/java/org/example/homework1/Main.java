package main.java.org.example.homework1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ThreadSafeArrayList<Integer> arrayList = new ThreadSafeArrayList<>();
        System.out.println("Enter integers separated by commas to add to ArrayList (enter -1 to finish):");
        String[] inputArray = scanner.next().split(",");
        try {
            for (String str : inputArray) {
                int num = Integer.parseInt(str);
                if (num == -1) {
                    break;
                }
                arrayList.add(num);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter integers separated by commas.");
        }

        System.out.println("ArrayList:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }

        ThreadSafeLinkedList<String> linkedList = new ThreadSafeLinkedList<>();
        System.out.println("\nEnter strings to add to LinkedList (enter 'exit' to finish):");
        String input;
        while (!(input = scanner.next()).equals("exit")) {
            linkedList.add(input);
        }

        System.out.println("\nLinkedList:");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }

        scanner.close();
        System.out.println("Program finished.");
    }
}
