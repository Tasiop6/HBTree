package main;

import hbtrees.HBTree;
import utils.GraphvizExporter;
import utils.ImageRenderer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HBTree tree = new HBTree();
        Scanner sc = new Scanner(System.in);

//        System.out.print("?: ");
        while (sc.hasNext()) {
            String command = sc.next();

            switch (command) {
                case "-f":
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Command!");
                        break;
                    }
                    int findKey = sc.nextInt();
                    System.out.println(findKey + (tree.find(findKey) ? " F" : " NF"));
                    break;

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

                case "-p":
                    System.out.printf("size: %d, max_height: %d, min_height: %d\n",
                            tree.getSize(), tree.getMaxHeight(), tree.getMinHeight());
                    tree.printPreOrder();
                    break;

                case "-g":
                    String dotFile = "images/tree.dot";
                    String imageFile = "images/tree.png";

                    GraphvizExporter.exportTree(tree, dotFile);
                    ImageRenderer.generateImage(dotFile, imageFile);

                    System.out.println("Tree exported to " + imageFile);
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
