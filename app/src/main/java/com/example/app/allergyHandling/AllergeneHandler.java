package com.example.app.allergyHandling;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


/**
 * AllergeneHandler.java
 * Purpose: Act as a management instance for your detected allergies and helps detect them when given
 * a list of ingredients.
 *
 * @author Jan Helmich
 * @version 1.0 20/01/18
 */
public class AllergeneHandler {
    private List<String[]> allergenes; // The type is a list of {<allergene>; <description>} String arrays
    private static String csvFile = "app/res/allergyInfo/allergenes.csv"; // TODO: Adjust accordingly to CSV file


    public AllergeneHandler() {
        allergenes = getAllergenesFromFile();
    }

    /**
     * Adds the given data to the dataset. If <name> is already registered, will overwrite the
     * description.
     *
     * @param name The name or identifier of an allergy ingredient.
     * @param description The description text for your given allergene.
     * @return A boolean value whether or not the process was successfull.
     */
    public boolean addAllergene(String name, String description) { // Use: Always put in the singular of the allergene
        try {
            name = name.toLowerCase();
            if (this.getAllergenes().contains(name)) {
                this.removeAllergy(name);
            }

            File fout = new File(csvFile);
            FileWriter fw = new FileWriter(fout.getAbsoluteFile(), true);

            BufferedWriter bw = new BufferedWriter(fw);

            bw.newLine();
            if (description.length() > 0) {
                bw.write(name + "," + description);
            } else {
                bw.write(name + "," + "<no description>");
            }
            bw.close();

            updateAllergenes();

            return true;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * @param allergy Checks whether or not an allergy with that identifier exists
     * @return boolean. True if allergy is found, false otherwise.
     */
    public boolean containsAllergy(String allergy) {
        allergy = allergy.toLowerCase();
        for (String[] s : allergenes) {
            if (s[0].equals(allergy) || (s[0] + "s").equals(allergy)) {
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @return Returns the list of allergene information stored in the allergenes.csv file.
     */
    public List<String[]> getAllergeneInfo() {
        return allergenes;
    }

    /**
     *
     * @return Same as getAllergeneInfor() but only returns identifiers of the allergies
     */
    public List<String> getAllergenes() {
        List<String> l = new LinkedList<>();
        for (String s[] : allergenes) {
            l.add(s[0]);
        }
        return l;
    }

    /**
     *
     *
     * @return Returns the content from the csv file.
     */
    private List<String[]> getAllergenesFromFile() {
        List<String[]> l = new LinkedList<>();

        String line = "1";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] vals = line.split(cvsSplitBy);
                if (vals.length > 1) {
                    System.out.println("Allergenes [name= " + vals[0] + " , comment=" + vals[1] + "]");
                    l.add(vals);
                } else {
                    String[] newvals = {vals[0],"No description"};
                    System.out.println(vals);
                    l.add(vals);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }


    private void updateAllergenes() {
        allergenes = getAllergenesFromFile();
    }

    /**
     *
     * @param allergy The name of the allergy information you want to remove
     * @return True if sucessfull, false otherwise.
     */
    public boolean removeAllergy(String allergy) {

        try {
            Boolean first = true;
            File inFile = new File(csvFile);
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return false;
            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().contains(allergy)) {

                    if (!first) {
                        pw.print("\n" + line);
                        pw.flush();
                    } else {
                        pw.print(line);
                        pw.flush();
                        first = false;
                    }
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return false;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
                return false;
            }
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tokens List of allergy ingredients
     * @return List of allergies that are known.
     */
    public List<String> allergicIngredients(List<String> tokens) {
        List<String> ingredients = new LinkedList<>();
        for (String token : tokens) {
            if (containsAllergy(token)) {
                ingredients.add(token);
            }
        }

        return ingredients;
    }


}
