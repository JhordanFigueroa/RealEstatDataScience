package edu.upenn.cit594.ui;

public class UserInterface {

    public static void mainMenu() {
        System.out.println();
        System.out.println("PLEASE INPUT THE NUMBER ASSOCIATED WITH ONE OF THE FOLLOWING OPTIONS: ");
        System.out.println("0 - Exit the program.");
        System.out.println("1 - Show the total population for all zip codes.");
        System.out.println("2 - Show the total parking fines per capita, for each zip code.");
        System.out.println("3 - Show the average market value of the residences for a specified zip code.");
        System.out.println("4 - Show the average total livable area of the residences for a specified zip code.");
        System.out.println("5 - Show the total residential market value, per capita, for a specified zip code.");
        System.out.println("6 - Show the total area value times fines per capita");
        System.out.println();
    }

    public static void specifyZipMenu(int choice){
        switch (choice){
            case 3:
                System.out.println("Please input a specific zip code to see the average market value of the residences: ");
                break;
            case 4:
                System.out.println("Please input a specific zip code to see the average total livable area of the residences: ");
                break;

            case 5:
                System.out.println("Please input a specific zip code to see the total residential market value, per capita: ");
                break;

            case 6:
                System.out.println("Please input a specific zip code to see....: ");
                break;
        }
    }

    public static void specifyMenuSelection(){
        System.out.println("Please input your selection: ");
    }
}
