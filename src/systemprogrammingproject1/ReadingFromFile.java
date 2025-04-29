
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemprogrammingproject1;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

/**
 *
 * @author hagam
 */
public class ReadingFromFile {
    
    
       public static void readAndWriteDataLineByLine(String file, String filePath) 
        { 
            HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
            myMap.remove("");
            File theFile = new File(filePath);

            try { 

                    // Create an object of filereader 
                    // class with CSV file as a parameter. 
                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord;

                    // create FileWriter object with file as parameter 
                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 


                    String[] header = { "loc", "st1", "st2", "st3","object code" }; 
                    writer.writeNext(header);
                    String[] data1 = { "1000", "COPY", "START", "1000" };
                    writer.writeNext(data1);
                    String [] test = csvReader.readNext();
                    test = csvReader.readNext();
                    test = csvReader.readNext();
                    String [] data3 = {data1[0], test[1], test[2], test[3]};
                    writer.writeNext(data3);


                    while ((nextRecord = csvReader.readNext()) != null) {



                        if(myMap.containsKey(data3[2])){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0], "3"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }
                        else if(data3[2].contains("WORD")){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0], "3"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }
                        else if(data3[2].contains("BYTE") && data3[3].charAt(0) == 'C'){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0], String.valueOf(data3[3].length()-3)), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }
                        else if(data3[2].contains("BYTE") && data3[3].charAt(0) == 'X'){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0], String.valueOf((data3[3].length()-3)/2)), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }
                        else if(data3[2].contains("RESW")){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0],Integer.toHexString(3*Integer.parseInt(data3[3]))), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }
                        else if(data3[2].contains("RESB")){

                            String[] data2 = { HexadecimalAddition.addHex(data3[0],Integer.toHexString(1*Integer.parseInt(data3[3]))), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                            writer.writeNext(data2);
                            data3 = data2;
                        }

                    }

                    writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
    
    
        }
       
       public static HashMap <String, String> creatingInstructionSetMap(String file)
       {
           HashMap <String, String> instructionSet = new HashMap<>();
           try { 
  
         
                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            instructionSet.put(nextRecord[0], nextRecord[1]); 
                    } 
            }    
            catch (Exception e) { 
                e.printStackTrace(); 
            }
           return instructionSet;
       }
       
       
       public static void creatingSymbolTable(String file, String filePath)
       {
           File theFile = new File(filePath);
           
           try { 
  
        
                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord;


                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 


                    String[] header = { "symbol", "loc"}; 
                    writer.writeNext(header);


                    while ((nextRecord = csvReader.readNext()) != null) {

                       if(nextRecord[1] !="" ){
                           String [] data = {nextRecord[1], nextRecord[0]};
                           writer.writeNext(data);
                       }

                    }

                    writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
       }
       
       
       public static void addObjectCode(String file, String filePath){
           
            HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
            myMap.remove("");
            HashMap<String, String> symbolTable = ReadingFromFile.covertSymbolTableToMap("data2.csv");
            symbolTable.remove("");

            File theFile = new File(filePath);

            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord;


                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 


                    String[] header = { "loc", "st1", "st2", "st3","object code" }; 
                    writer.writeNext(header);
                    String [] test = csvReader.readNext();


                    while ((nextRecord = csvReader.readNext()) != null) {

           //                if(myMap.containsKey(nextRecord[2]) && (nextRecord[3].charAt((nextRecord[3].length()-1)) == 'X' && nextRecord[3].charAt((nextRecord[3].length()-2)) == ',')){
           //                    String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + HexadecimalAddition.addHex(symbolTable.get(nextRecord[3].substring(0, nextRecord[3].length()-2)), "8000")};
           //                    writer.writeNext(data);
           //                }

                       if(myMap.containsKey(nextRecord[2])){
                             if(nextRecord[3] == ""){

                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + "0000"};
                                 writer.writeNext(data);
                             }
                             else if((nextRecord[3].charAt((nextRecord[3].length()-1)) == 'X' && nextRecord[3].charAt((nextRecord[3].length()-2)) == ',')){

                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + HexadecimalAddition.addHex(symbolTable.get(nextRecord[3].substring(0, nextRecord[3].length()-2)), "8000")};
                                 writer.writeNext(data);
                             }
                             else{

                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + symbolTable.get(nextRecord[3])};
                                 writer.writeNext(data);
                             }

                       }
                       else if(nextRecord[2].contains("WORD")){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], Integer.toHexString(Integer.parseInt(nextRecord[3]))};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("RESW")|| nextRecord[2].contains("RESB")){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], "No Object Code"};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("BYTE") && nextRecord[3].charAt(0) == 'X'){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[3].substring(2, nextRecord[3].length()-1)};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("BYTE") && nextRecord[3].charAt(0) == 'C'){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3],ReadingFromFile.convertStringToHex(nextRecord[3].substring(2, (nextRecord[3].length()-1)))};
                           writer.writeNext(data);
                       } 

                    }

                    writer.close();

             } 
             catch (Exception e) { 
             e.printStackTrace(); 
             }
        }
       
        public static String convertStringToHex(String str) {

            StringBuffer hex = new StringBuffer();

            // loop chars one by one
            for (char temp : str.toCharArray()) {

                // convert char to int, for char `a` decimal 97
                int decimal = (int) temp;

                // convert int to hex, for decimal 97 hex 61
                hex.append(Integer.toHexString(decimal));
            }

            return hex.toString();

     }
        
        public static HashMap <String, String> covertSymbolTableToMap(String file)
       {
            HashMap <String, String> symbolTable = new HashMap<>();
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            symbolTable.put(nextRecord[0], nextRecord[1]); 
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return symbolTable;
        }
        
//        public static void createHteRecord(String file, String filePath){
//            
//            HashMap <String, String> helperMap = mappingLocationCounterToObjectCode("data3.csv");
//            File theFile = new File(filePath);
//            try{
//                
//                FileWriter outputfile = new FileWriter(theFile); 
//                CSVWriter writer = new CSVWriter(outputfile);
//                String[] nextRecord;
//                
//                for(String s: helperMap.keySet()){
////                    if(nextRecord == null){
////                        nextRecord = {"H", s,"gg"};
////                        writer.writeNext(nextRecord);
////                    }
//
//                }
//            }
//            catch (Exception e) { 
//            e.printStackTrace(); 
//            }
//        }
        
        
        public static HashMap<String, String> mappingLocationCounterToObjectCode(String file){
            
            HashMap <String, String> myMap = new HashMap<>();
            
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            myMap.put(nextRecord[0], nextRecord[4]); 
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return myMap;
        }
        
        public static void creatingHteRecord(String file, String helperFile, String filePath){
            HashMap <String, String> myMap = covertSymbolTableToMap("data2.csv");

            File theFile = new File(filePath);

            try { 

                    // Create an object of filereader 
                    // class with CSV file as a parameter. 
                    FileReader filereader = new FileReader(file); 
                    FileReader filereader2 = new FileReader(helperFile);

                    CSVReader csvReader = new CSVReader(filereader);
                    String[] nextRecord = csvReader.readNext();
                    nextRecord = csvReader.readNext();
                    
                    CSVReader csvReader2 = new CSVReader(filereader2);
                    String[] nextRecord2;
                    String programName = "";
                    
                    
                    while ((nextRecord2 = csvReader2.readNext()) != null){
                        if(nextRecord2[2].contains("START")){
                            programName = nextRecord2[1];
                            break;
                        }
                    }
                    
                    
                    // create FileWriter object with file as parameter 
                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 
                    
                    String[] header = {"H", programName, myMap.get("FIRST"), HexadecimalSubtraction.subtractHex(HexadecimalAddition.addHex(myMap.get("OUTPUT"), "1"), myMap.get("FIRST"))};
                    writer.writeNext(header);
                    
                    while (nextRecord != null) {
                        System.out.println(nextRecord[0]);
                        int count = 0;
                        String[]objArr = new String[10];
                        String start = nextRecord[0];
                        while(nextRecord != null && !nextRecord[4].equals("No Object Code") && count < 10 ){
                            objArr[count] = nextRecord[4];
                            nextRecord = csvReader.readNext();
                            count++;
                        }
                        String end;
                        if(nextRecord != null){
                           end = nextRecord[0]; 
                        }
                        else{
                            end = HexadecimalAddition.addHex(myMap.get("OUTPUT"), "1");
                        }
                        
                        String[]newObjArr = new String[objArr.length+3];
                        newObjArr[0] = "T";
                        newObjArr[1] = start;
                        newObjArr[2] = HexadecimalSubtraction.subtractHex(end, start);
                        for(int i = 3; i< newObjArr.length; i++){
                            newObjArr[i] = objArr[i-3];
                        }
                        writer.writeNext(newObjArr);
                        
                        while(nextRecord != null && nextRecord[4].equals("No Object Code") ){
                            nextRecord = csvReader.readNext();
                        }
                    }
                    String [] tail = {"E", myMap.get("FIRST")};
                    writer.writeNext(tail);

                    writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
        }
}
