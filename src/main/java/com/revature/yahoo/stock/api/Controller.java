package com.revature.yahoo.stock.api;

import java.util.Scanner;

public class Controller {

    public Controller() {
        AppStart();
    }

    private void AppStart() {
        int selection;
        boolean isExit = false;

        do {
            Menu();
            selection = new Scanner(System.in).nextInt();
        } while (!isExit);

    }

    public void Menu() {
        StringBuilder menu = new StringBuilder("\tWelcome to the Stock History App\n")
                .append("1. Select 1 to reprint menu\n")
                .append("2. Select 2 to watch the most recent stock\n")
                .append("8. Select 8 to exit");
    }


}
