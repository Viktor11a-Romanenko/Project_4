package view;

import model.User;
import service.MainService;

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
            System.out.println("\nMake your choice");

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
private void showLibraryMenu(){
        while (true){
            System.out.println("Library Menu");
            System.out.println("1. Take a book");
            System.out.println("2. Turn book");
            System.out.println("0. Back to the future");

            System.out.println("\nMake your choice");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) break;

        }

}
    private void showReaderMenu(){
        while (true){
            System.out.println("Reader Menu");
            System.out.println("1. Login");
            System.out.println("2. Registration");
            System.out.println("3. Logout");
            System.out.println("0. Back to the future");

            System.out.println("\nMake your choice");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) break;

            handleReaderMenuChoice(input);

        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("Admin menu");
            System.out.println("1. Check Reader");
            System.out.println("2. Block Reader");
            System.out.println("0. Back to the future");

            System.out.println("\nMake your choice");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) break;

        }
    }


private void handleReaderMenuChoice(int input){
        switch (input){
            case 1:
                System.out.println("Welcome to library menu");
                waitRead();
                break;
            case 2:
                System.out.println("Registration of new Reader");
                System.out.println("Put in your email");
                String email = scanner.nextLine();

                System.out.println("Put in your password");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null){
                    System.out.println("Congratulations, you are in");
                }else {
                    System.out.println("Houston we have a problem here");
                }
                waitRead();

                break;
            case 3:
                service.logout();
                System.out.println("You are got out");
                waitRead();
                break;
            default:
                System.out.println("\nWrong password");
            }
        }

private void waitRead(){
    System.out.println("\nFor continuing press Enter");
    scanner.nextLine();
}


}



