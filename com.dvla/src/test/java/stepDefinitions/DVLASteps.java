package stepDefinitions;

import Utilities.CSVUtil;
import Utilities.Config;
import Utilities.ExcelUtil;
import Utilities.FileServiceBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ConfirmVehicleDetails;
import pages.GetVehicleInfo;
import pages.RegistrationSearch;

import static Utilities.Config.INVALID_VEHICLE_REPO;
import static Utilities.Config.VEHICLE_REPO;
import static Utilities.Driver.logger;

public class DVLASteps {
    String[] validFiles;
    FileServiceBean fileServiceBean = new FileServiceBean();
    String Folder_Path="";

    @Given("^I access all the files in the specified directory$")
    public void i_access_all_the_files_in_the_specified_directory() {
        fileServiceBean.SetFolderPath(VEHICLE_REPO);
        Folder_Path = VEHICLE_REPO;
    }

    @Given("^I access all the files in the specified invalid directory$")
    public void i_access_all_the_files_in_the_specified_invalid_directory() {
        fileServiceBean.SetFolderPath(INVALID_VEHICLE_REPO);
        Folder_Path = INVALID_VEHICLE_REPO;
    }

    @When("^I want to list all the files in the directory$")
    public void i_want_to_list_all_the_files_in_the_directory() {
        logger.info("Reading all files within the folder supplied and print the relavent File Details\n");
    }

    @Then("^Filenames of all the files in the directory are listed$")
    public void filenames_of_all_the_files_in_the_directory_are_listed() {
        fileServiceBean.ListAllFilesInFolder();
    }

    @When("^I want to list only the valid files in the directory$")
    public void i_want_to_list_only_the_valid_files_in_the_directory() {
        logger.info("Listing all Valid files within the folder supplied\n");
    }

    @Then("^Filenames of all valid files in the directory are listed$")
    public void filenames_of_all_valid_files_in_the_directory_are_listed() {
        fileServiceBean.ListAllValidFilesInFolder();
        validFiles = fileServiceBean.getValidFiles();
    }

    @When("^I want to list only the invalid files in the directory$")
    public void i_want_to_list_only_the_invalid_files_in_the_directory() {
        logger.info("Listing all Invalid files within the folder supplied\n");
    }

    @Then("^Filenames of all invalid files in the directory are listed$")
    public void filenames_of_all_invalid_files_in_the_directory_are_listed() {
        fileServiceBean.ListAllInvalidFilesInFolder();
    }

    @Given("^I access all valid files in the specified directory$")
    public void i_access_all_valid_files_in_the_specified_directory() {
        //Take all the file names within the folder into memory
        fileServiceBean.SetFolderPath(VEHICLE_REPO);
//        validFiles = fileServiceBean.getValidFiles();
    }

    @When("^I Open DVLA Website and Search for the Vehicle details$")
    public void i_Open_DVLA_Website_and_Search_for_the_Vehicle_details() {
        GetVehicleInfo vehicleInfo = new GetVehicleInfo();
        vehicleInfo.StartNow();
    }

    @Then("^Vehicle details displayed are matches to the file contents$")
    public void vehicle_details_displayed_are_matches_to_the_file_contents() {

        String registration, make, colour;
        RegistrationSearch search = new RegistrationSearch();
        ConfirmVehicleDetails confirmDetails = new ConfirmVehicleDetails();
        //Take all the file names within the folder into memory
        String[] files = validFiles;  //fileServiceBean.getValidFiles();
        String[][] ExcelData;
        for (String file : files) {

            //Find matching XLS Files
            if (file.contains("xls")) {
                System.out.printf("\nReading the Vehicle Details from XLS : '%s' \n", file);
                //System.out.printf("C:\\IdeaProjects\\com.dvla\\src\\test\\resources\\files\\" + file);
                ExcelUtil.setExcelFile("C:\\IdeaProjects\\com.dvla\\src\\test\\resources\\invalidfiles\\" + file);        //Opens Test Data Excel File
                ExcelUtil.setExcelFile(Folder_Path + "\\" + file);
                ExcelUtil.setExcelSheet(0);

                //Read the file contents into a multidimensional Array
                ExcelData = ExcelUtil.Read_Data();
                for (int rowNum = 0; rowNum < ExcelData.length; rowNum++) {
                    registration = ExcelData[rowNum][0];
                    make = ExcelData[rowNum][1];
                    colour = ExcelData[rowNum][2];

                    //Find Vehicle Details and Verify the details
                    search.Search(registration);
                    confirmDetails.VerifyVehicleDetails(registration, make, colour);
                    confirmDetails.NavigateBack();
                }
            }
            //Find matching CSV Files
            else if (file.contains("csv")) {
                System.out.printf("\nReading the Vehicle Details from CSV : '%s' \n", file);
                CSVUtil.setCSVFile(Folder_Path +"\\" + file);

                //Read the file contents into a multidimensional Array
                ExcelData = CSVUtil.Read_Data();
                for (int rowNum = 1; rowNum < ExcelData.length; rowNum++) {
                    registration = ExcelData[rowNum][0];
                    make = ExcelData[rowNum][1];
                    colour = ExcelData[rowNum][2];

                    //Find Vehicle Details and Verify the details
                    search.Search(registration);
                    confirmDetails.VerifyVehicleDetails(registration, make, colour);
                    confirmDetails.NavigateBack();
                }
            }
        }
    }
}

