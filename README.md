# RealEstatDataScience
Team 29 Project
    - Jhordan Figueroa
    - Chris Payne
    
##Description
This was the final team project for University of Pennsylvania's CIT 594 Data Structures and Software Design Course.
The goal of the project was to use real estate data from different input files: CSV, TEXT, and JSON. Once the data was 
parsed, using efficient software techniques and data structures, we created algorithms to answer a set of questions. 

[1] ADDITIONAL FEATURE
    - The additional feature is computing for each zip code price per livable space times the fines per capita.
    The point of this computation is to access how much the property values of a particular zip code relate
    to the amount of fines they get per population. This feature was validated using our other calculations, by
    making sure we were getting expected results that had similar computations. Additionally, we used software
    called Alteryx to validate our results.

[2] USE OF DATA STRUCTURES
    - ArrayList<CustomObject>
        - We created custom objects to store the data parsed from the provided input files.
        Creating custom objects allowed for the flexibility to specify exactly which fields of data we wanted to keep
        (e.g. Zip Code, Market Value, State, etc.)
        - ArrayLists<CustomObjects> were the choice data structure to store the parsed data because there are no
        fixed size limits and iterating through the ArrayList is simple --- we can use a forEach loop.
    - TreeMap<Integer, Integer>
        - We chose to use a TreeMap to store the population data, with the Zip Codes (integers) being the keys, and
        the sum of the populations for each zip code (also an integer) being stored as the values. The TreeMap was
        populated by iterating through an ArrayList<PopulationObj> (see above bullet).
        - The advantages of using a TreeMap are:
            - No duplicate key values are allowed, so there was no risk of a zip code not including all of the
            the associated data (after cleansing the data, of course)
            - The TreeMap automatically sorts its keys in ascending order. This "front loads" the sorting effort as the
            TreeMap is populated, instead of having to sort the data structure by key values after the data structure has
            been filled. Since the project called for the use of memoization, the overhead required to sort by key values
            in a TreeMap was minimal, as the entire data structure was being populated anyway.
    - HashMap<Integer, Long[]>
        - We chose to use a HashMap to store the residential data, since we wanted to be use the zip codes
        as keys, while being able to aggregate data as different values in a Long[] array.
        - A HashMap was the more efficient data structure since it doesn't spend the extra overhead sorting the data, as
        is the case with a TreeMap.
        
###How to download and use
Copy the repository and clone to your local machine. You will most likely need to add Json simple-1.1.1.jar file to your
project structure. You can download it from here: https://code.google.com/archive/p/json-simple/downloads. 
Add this to your project structure. Additionally, don't forget to update the configuration for your Main class. You will
need to specify the arguments. There should be a total of 5 arguments. Each is spaced out by a space. The input files 
were too big to be uploaded onto github, so I included them in the gitignore file. You can use your own input files or 
go to the public website of housing data for philadelphia. 