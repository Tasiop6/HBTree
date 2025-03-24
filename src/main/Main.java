package main;

import hbtrees.HBTree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HBTree tree = new HBTree();
        Scanner sc = new Scanner(System.in);

//        System.out.print("?: ");
        while (sc.hasNext()) {
            String command = sc.next();

            switch (command) {
                case "-i":
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Command!");
                        break;
                    }
                    int insertKey = sc.nextInt();
                    System.out.println(insertKey + (tree.insert(insertKey) ? " I" : " NI"));
                    break;

                case "-d":
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Command!");
                        break;
                    }
                    int deleteKey = sc.nextInt();
                    System.out.println(deleteKey + (tree.delete(deleteKey) ? " D" : " ND"));
                    break;

                case "-f":
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Command!");
                        break;
                    }
                    int findKey = sc.nextInt();
                    System.out.println(findKey + (tree.find(findKey) ? " F" : " NF"));
                    break;

                case "-q":
                    System.out.println("Bye bye!");
                    return;

                default:
                    System.out.println("Invalid Command!");
            }

//            System.out.print("?: ");
        }

        sc.close();
    }
}
