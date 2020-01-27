package org.csvHandler;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public  class ReportToJson {
//    public static void createNewFile(String fileName){
//        try {
//            if ((new File(fileName)).exists()) {
//                System.out.println(fileName+ " - file already exsist!");
//            } else {
//                File file = new File("/"+fileName);
//                file.createNewFile();
//                System.out.println(fileName+" - is created successfully");
//            }
//        }
//        catch(Exception e){
//            System.out.println("Error to create new File "+ fileName);
//            System.out.println("\n"+e.getMessage());
//        }
//    }

    public static void reportToJsonTwo(ArrayList<String[]> list, ArrayList<Entity> arrayList, String fileName) throws IOException {
        //createNewFile(fileName);

        for(int i=0; i<list.size(); i++){
            for(int j =0; j<arrayList.size(); j++){
                if(list.get(i)[0].equals(arrayList.get(j).getName())) {
                    list.get(i)[1] = String.valueOf(arrayList.get(j).getSum());
                }
            }
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for  (String[] str:list) {
                if(tryParseToInt(str[1])!=0)
                    object.put(str[0], tryParseToInt(str[1]));
                else
                    object.put(str[0], null);
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("Report successfully generated - "+fileName);

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Report not generated - "+fileName);
        }
    }
    public static int tryParseToInt(String str){
        try{
            return Integer.parseInt(str);
        }
        catch (Exception e){
            return 0;
        }
    }

    public static void reportToJsonOne(ArrayList<Entity> list, String fileName){
        //createNewFile(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for  (Entity entity:list) {
                object.put(entity.getName(), entity.getSum());
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("Report successfully generated - "+fileName);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Report not generated - "+fileName);
        }
    }
    public static void reportToJsonThree(ArrayList<Entity> list, String fileName){
        //createNewFile(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for  (Entity entity:list) {
                object.put(entity.getName(), entity.getArrayList());
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("Report successfully generated - "+fileName);

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Report not generated - "+fileName);
        }
    }
}
