/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package systemprogrammingproject1;

import com.opencsv.CSVReader;
import edu.duke.*;
import java.io.File;
import java.io.FileReader;
import java.lang.*;
import java.util.HashMap;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



/**
 *
 * @author hagam
 */
public class SystemProgrammingProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
       
       //ReadingFromFile.readDataLineByLine("System programming project input.csv");
       //HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
        //System.out.println(myMap);
        //ReadingFromFile.readAndWriteDataLineByLine("test.csv", "C:\\Users\\hagam\\Documents\\NetBeansProjects\\SystemProgrammingProject1\\data.csv");
        //ReadingFromFile.creatingSymbolTable("data.csv", "C:\\Users\\hagam\\Documents\\NetBeansProjects\\SystemProgrammingProject1\\data2.csv");
    //ReadingFromFile.addObjectCode("data.csv", "C:\\Users\\hagam\\Documents\\NetBeansProjects\\SystemProgrammingProject1\\data5.csv");
    ReadingFromFile.creatingHteRecord("data5.csv", "data.csv" ,"C:\\Users\\hagam\\Documents\\NetBeansProjects\\SystemProgrammingProject1\\data8.csv");
    //System.out.println(HexadecimalSubtraction.subtractHex("D1", "C5"));
        //System.out.println(ReadingFromFile.covertSymbolTableToMap("data2.csv"));

       
       
}
    
}
