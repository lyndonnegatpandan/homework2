    
package com.homework.two;

import java.util.*;
import java.io.*;
import org.apache.commons.lang3.StringUtils;

public class Homework2 {

    public String randomGenerator() {
        Random r = new Random();
        StringBuilder a = new StringBuilder(3); //String builder with capacity of 3
        String ascii = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+";
        for (int i = 0; i < 3; i++) {
            a.append(ascii.charAt(r.nextInt(ascii.length()))); //Randomizer of characters with length of 3.
        }
        return a.toString();
    }

    public void dataEncoder(Map<String, String> map, Object[][] cell, int rowNum, int colNum) {
        Object[] objectArray;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                map.put(randomGenerator(), randomGenerator());
            }
            objectArray = map.entrySet().toArray();
            cell[i] = objectArray;
            map.clear();
        }
    }
    
    public String[][] ObjectToArray(Object[][] cell, int row, int col){
        String[][] str= new String[row][col];
        
        System.out.println(Arrays.deepToString(cell));
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                str[i][j]=cell[i][j].toString();
//                System.out.println(str[i][j]);
//                
//                String s = str[i][j].replace("=",",");
//                str[i][j]=s;
                
            }
            
        }
        return(str);
    }

    public void search(String[][] cell) {

        Scanner sc = new Scanner(System.in);
        int index = 0;
        int count = 0;

        System.out.print("Enter value to search: "); //User inputs the value.
        String valueToSearch = sc.next();

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                String a = cell[i][j];
                String[] b = StringUtils.split(a,"=");
                while ((index = StringUtils.indexOf(b[0], valueToSearch, index)) != -1) {
                    count++;
                    index++;
                    System.out.println("[" + i + "," + j + "]" + "-" + count + " From key field");
                }
            }
            count = 0;

        }

        index = 0;
        count = 0;
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                String a = cell[i][j];
                String[] b = StringUtils.split(a,"=");
                while ((index = StringUtils.indexOf(b[1], valueToSearch, index)) != -1) {
                    count++;
                    index++;
                    System.out.println("[" + i + "," + j + "]" + "-" + count + " From value field");
                }
            }
            count = 0;
            System.out.println();
        }

    }
    

    public void editData(String[][] cell) {
        try{
        // System.out.println("here");
        // System.out.println(Arrays.deepToString(cell));
        
        String choice;
        Scanner sc = new Scanner(System.in);
        System.out.print("Array index x: "); //Input x and y coordinates. 
        int xCoordinate = sc.nextInt();
        System.out.print("Array index y: ");
        int yCoordinate = sc.nextInt();
        System.out.print("Key or value? ");
        choice = sc.next();
        String newString;
        String val;
        String a;
        String[] b;
        switch (choice) {
            case "Key":
                System.out.print("New Value: ");
                val = sc.next();
                a = cell[xCoordinate][yCoordinate];
                b = StringUtils.split(a,"=");;
                newString = val + "=" + b[1];
                cell[xCoordinate][yCoordinate] = newString;
                break;
            case "Value":
                System.out.print("New Value: ");
                val = sc.next();
                a = cell[xCoordinate][yCoordinate];
                b = StringUtils.split(a,"=");;
                newString = b[0] + "=" + val;
                cell[xCoordinate][yCoordinate] = newString;
                break;

            default:
                System.out.println("Invalid Choice");
                break;

        }
    }
        catch(Exception e){
            System.out.println("here"+e);
        }

    }

    public void printArray(String[][] cell) {
        
        for(String[] a:cell){
            for(String b:a){
                String temp = b.replace("=",",");
                System.out.print(temp + " ");
            }
            System.out.println(" ");
        }

    }

    public void saveFile(String[][] cell) {
        String Holder;
        String rep;
        String[][] temp = new String[cell.length][cell[0].length];

        
        try {
            String path = "C:/Users/MARVIN/Desktop/codes/Output.txt";
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[0].length; j++) {
                    Holder = (cell[i][j]);
                    temp[i][j] = Holder;
                    if (j == temp[0].length - 1) {
                        writer.write(temp[i][j]);
                    } else {
                        writer.write(temp[i][j] + " ");
                    }
                }
                writer.write("\n");
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }

    }
    
    public static String[][] readFile() throws IOException {
        int row = 0;
        int col = 0;
        int index = 0;
        String path = "C:/Users/MARVIN/Desktop/codes/Output.txt";
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        Scanner scanner;
        scanner = new Scanner(file);

        while (br.readLine() != null) {
            row++;
        }

        String current = scanner.nextLine();
        while ((index = StringUtils.indexOf(current," ", index)) != -1) {
            col++;
            index++;
        }

        String[][] temp = new String[row][col+1];
        Scanner sc = new Scanner(loadTextFile());
        System.out.println(row);
        System.out.println(col);
        while (sc.hasNextLine()) {
            for (int i = 0; i < temp.length; i++) {
                String[] line = sc.nextLine().split(" ");
                for (int j = 0; j < line.length; j++) {
                    temp[i][j] = line[j];
                    
                }
            }
            
        }
        scanner.close();
        fr.close();
        return temp;
    }

    private static File loadTextFile() {
        File file = new File("C:/Users/MARVIN/Desktop/codes/Output.txt");
        return file;
    }

    public void sortTable(String[][] cell) {
        String[][] temp = new String[cell.length][cell[0].length];
        String choice;
        Scanner sc = new Scanner(System.in);
        System.out.print("Sort Ascending or Descending? ");
        choice = sc.next();
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                String Holder = (cell[i][j]);
                String replace = Holder.replace("=", ",");
                temp[i][j] = replace;

            }

        }
        switch (choice) {
            case "Ascending":
                for (int i = 0; i < temp.length; i++) {
                    Arrays.sort(temp[i]);
                }

                for (int i = 0; i < temp.length; i++) {
                    for (int j = 0; j < temp[0].length; j++) {
                        System.out.print(temp[i][j]);
                        System.out.print(" ");

                    }
                    System.out.println();

                }
                break;
            case "Descending":
                for (int i = 0; i < temp.length; i++) {
                    Arrays.sort(temp[i], Collections.reverseOrder());
                }

                for (int i = 0; i < temp.length; i++) {
                    for (int j = 0; j < temp[0].length; j++) {
                        System.out.print(temp[i][j]);
                        System.out.print(" ");

                    }
                    System.out.println();

                }
                break;
            default:
                System.out.println("Invalid Choice");
                break;

        }

    }
    
    public String[][] addColumn(String[][] cell){
        String[][] temp = new String[cell.length][cell[0].length+1];
        try{
        Scanner sc = new Scanner(System.in);
        
        int row;
        String key;
        String val;
        System.out.print("Which row you want to add a column? ");
        row=sc.nextInt();
        System.out.print("Key: ");
        key=sc.next();
        System.out.print("Value: ");
        val=sc.next();
        
        for(int i = 0; i < cell.length; i++){
            for(int j = 0; j < cell[0].length+1; j++){
                if(j==cell[0].length && i == row){
                    temp[i][j] = key + "=" + val;
                }else if(j == cell[0].length){
                    temp[0][j]="null=null";
                }else{
                    temp[i][j]=cell[i][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(temp));
        
        
        
        }catch(Exception e){
            System.out.println("here column"+e);
        }
        return temp;
        
    }


    public static void main(String args[]) {
        Homework2 Hw2 = new Homework2();
        Map<String, String> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Object[][] cell = null;
        String[][] table = null;
        String choice;
        int rowNum;
        int colNum;
        System.out.print("Do you want to load text file [yes][n]? ");
        choice = sc.next();

        try {
            if (StringUtils.equals(choice,"yes")) {
                table=readFile();
            } else {
                System.out.print("Enter the number of row: ");
                rowNum = sc.nextInt();
                System.out.print("Enter the number of column: ");
                colNum = sc.nextInt();
                cell = new Object[rowNum][colNum];
                Hw2.dataEncoder(map, cell, rowNum, colNum);
                
                table=Hw2.ObjectToArray(cell, rowNum, colNum);
                Hw2.printArray(table);
                Hw2.saveFile(table);
            }

            while (true) {
                System.out.println("Main Menu");
                System.out.println("1.) Reset Array");
                System.out.println("2.) Edit Array");
                System.out.println("3.) Search Array");
                System.out.println("4.) View Array");
                System.out.println("5.) Sort Array");
                System.out.println("6.) Add column");
                System.out.print("Choice: ");
                int a = sc.nextInt();

                switch (a) {
                    case 1:
                        System.out.print("Enter the number of row: ");
                        rowNum = sc.nextInt();
                        System.out.print("Enter the number of column: ");
                        colNum = sc.nextInt();
                        cell = new Object[rowNum][colNum];
                        Hw2.dataEncoder(map, cell, rowNum, colNum);
                        System.out.println(Arrays.deepToString(cell));
                        table=Hw2.ObjectToArray(cell, rowNum, colNum);
                        Hw2.saveFile(table);
                        Hw2.printArray(table);
                        break;
                    case 2:
                        Hw2.editData(table);
                        Hw2.saveFile(table);
                        Hw2.printArray(table);
                        
                        break;
                    case 3:
                        System.out.println(Arrays.deepToString(table));
                        Hw2.search(table);
                        break;
                    case 4:
                        Hw2.printArray(table);
                        break;
                    case 5:
                        Hw2.sortTable(table);
                        break;
                    case 6:
                        table=Hw2.addColumn(table);
                        Hw2.saveFile(table);
                        Hw2.printArray(table);
                        break;
                    default:
                        System.out.println("Invalid");
                        break;
                }

            }

        }  catch (InputMismatchException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
