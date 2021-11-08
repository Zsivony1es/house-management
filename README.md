# Housing Management / Hausverwaltung
Simple house management program in Java, that was made for a university project

This program uses the built-in serialization tools of Java, to save a list of flats managed by the system. It will create a ```.ser``` file, which can be read from,  written to and modified by this program.

### How to use

The program can be ran from the terminal/command prompt using the Java compiler. The file that must be executed is the ```/src/HausverwaltungClient.java``` file, so every command should have the form of

```
java HausverwaltungClient <DataFileName>.ser <OtherArguments>
```

The following arguments can be used to run the application:

+ 'list'
  + All flats in the file with their data will be listed
+ 'list \<id\>'
  + Only the flat with ID \<id\> will be shown
+ 'add EW \<id\> \<area\> \<numberOfRooms\> \<floor\> \<yearOfConstruction\> \<postalCode\> \<streetName\> \<houseNumber\> \<flatNumber\> \<utilityCost/m2\> \<extraCost/m2\>'
  + Add an occupant-owned flat to the file, with the arguments listed above
+ 'add MW \<id\> \<area\> \<numberOfRooms\> \<floor\> \<yearOfConstruction\> \<postalCode\> \<streetName\> \<houseNumber\> \<flatNumber\> \<rent/m2\> \<numberOfTenants\>'
  +  Add an rented flat to the file, with the arguments listed above
+ 'delete \<id\>'
  + Delete the apartment with ID \<id\> from the file
+ 'count'
  + Returns the number of saved apartments in the file
+ 'count \<EW/MW\>'
  + Returns the number of occupant-owned/rented apartments in the file
+ 'meancosts'
  + Returns the average monthly costs of all saved apartments (to see how it is exactly calculated, check the overwritten class method ```gesamtKosten()``` for the class MietWohnung and EigentumsWohnung)
+ 'oldest'
  + Returns the oldest apartment(s) in the list. If 2 (or more) are built in the same year and they are the oldest, then all of them will be listed.

