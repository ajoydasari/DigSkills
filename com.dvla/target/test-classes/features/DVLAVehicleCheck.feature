
Feature: DVLA Vehicle Details Feature - open DVLA Vehicle Details Page and check the Make and Colour details of the vehicles supplied in CSV and Excel files

  Scenario: List all the files in the supplied files
    Given I access all the files in the specified directory
    When I want to list all the files in the directory
    Then Filenames of all the files in the directory are listed

    When I want to list only the valid files in the directory
    Then Filenames of all valid files in the directory are listed

    When I want to list only the invalid files in the directory
    Then Filenames of all invalid files in the directory are listed

    When I Open DVLA Website and Search for the Vehicle details
    Then Vehicle details displayed are matches to the file contents



  Scenario: Verify Vehicle Details - Invlid details
    Given I access all the files in the specified invalid directory
    Then Filenames of all valid files in the directory are listed
    When I Open DVLA Website and Search for the Vehicle details
    Then Vehicle details displayed are matches to the file contents
