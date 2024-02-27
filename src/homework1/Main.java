package homework1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ThreadSafeArrayList<Integer> arrayList = new ThreadSafeArrayList<>();
        System.out.println("Enter integers to add to ArrayList (enter -1 to finish):");
        int num;
        while ((num = scanner.nextInt()) != -1){
            arrayList.add(num);
        }

        System.out.println("ArrayList:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }


        ThreadSafeLinkedList<String> linkedList = new ThreadSafeLinkedList<>();
        System.out.println("\nEnter strings to add to LinkedList (enter 'exit' to finish):");
        String input;
        while (!(input = scanner.next()).equals("exit")){
            linkedList.add(input);
        }

        System.out.println("\nLinkedList:");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }

        scanner.close();
    }
}