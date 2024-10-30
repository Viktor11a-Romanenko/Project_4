package view;

import java.util.Scanner;

public class Menu {
    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void run(){

        showMenu();
    }
private void showMenu(){
        while (true) {
            System.out.println("Welcome to library readers");
            System.out.println("1. Do you want to read?");
            System.out.println("2. Do you want to smoke?");
            System.out.println("3. Are you admin?");
            System.out.println("0. Get out");
            System.out.println("Make your choice");

            int choice = scanner.nextByte();
            scanner.nextLine();


            if (choice == 0) {
                System.out.println("See you later!");
                System.exit(0);
            }
            showSubMenu(choice);
        }
}

private void showSubMenu(int choice){
        switch (choice){
            case 1:
                showLibraryMenu();
                break;
            case 2:
                showReaderMenu();
                break;
            case 3:
                showAdminMenu();
                break;
            default:
                System.out.println("Make correct choice");

        }

}


}
