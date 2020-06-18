package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.ui.ErrorMessages;
import edu.upenn.cit594.ui.Results;
import edu.upenn.cit594.ui.UserInterface;

import java.io.IOException;

/***
 * This class handles which calculations to perform based on user inputs
 */
public class Handler {
    int userZipChoice = 0;
    int userChoice;

    public Handler() throws IOException {
        // prompt
        handleUserMenuSelection();

        switch (userChoice) {

            case 0:
                Logger.getInstance().printToLog(String.valueOf(System.currentTimeMillis()));
                Globals.scan.close();
                System.exit(0);
                break;

            case 1: // display the total population of the file -- it should equal 1526206 using the data provided in Coursera
                Results.printTotalPopulation(new PopulationByZip().getTotalPop());
                break;

            case 2: // display total fines per capita
                new TotalFinesPerCapita().getFinesPerCapita();
                break;

            case 3: // display average residential market value for specified zip code
                promptZipChoice();
                new ResidentialCalcs().averageResidentialMarketValue(userZipChoice);
                break;

            case 4: // display average livable area for specified zip code
                promptZipChoice();
                new ResidentialCalcs().averageResidentialLivableArea(userZipChoice);
                break;

            case 5:
                promptZipChoice();
                new ResidentialCalcs().getMktValuePerCap(userZipChoice);
                break;

            case 6:
                new TotalAreaValueTimesFinesPerCapita().getAreaValueTimesFinesPerCapita();
                break;
        }

        new Handler();
    }

    private void promptZipChoice() throws IOException {
        do {
            UserInterface.specifyZipMenu(userChoice);
            while (!Globals.scan.hasNext("\\d{5}")){
                ErrorMessages.incorrectZipInput();
                Globals.scan.next();
            }
            userZipChoice = Globals.scan.nextInt();
            Logger.getInstance().printToLog(System.currentTimeMillis() + " " + userZipChoice);
        } while (userZipChoice <= 0);
    }

    public void handleUserMenuSelection() throws IOException {
        UserInterface.mainMenu();
        while(true){
            UserInterface.specifyMenuSelection();
            String input = Globals.scan.next();
            Logger.getInstance().printToLog(System.currentTimeMillis() + " " + input);
            int inputValue = 0;
            try {
                inputValue = Integer.parseInt(input);
                if(inputValue >= 0 && inputValue <= 6){
                    userChoice = inputValue;
                    break;
                } else {
                    ErrorMessages.incorrectRangeInput();
                }
            } catch(Exception e){
                ErrorMessages.incorrectMainMenuInput();
            }
        }
    }
}
