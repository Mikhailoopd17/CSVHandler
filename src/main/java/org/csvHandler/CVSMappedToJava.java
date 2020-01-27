package org.csvHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;

import static org.csvHandler.ReportToJson.*;
import static org.csvHandler.Util.*;

public class CVSMappedToJava {

    public static void main(String[] args) throws IOException {
        String path = "/";
        ArrayList<String[]> entitysAll = new ArrayList<>();
        String[] files = searchFiles(path);

        if(files.length >0){
            for(String file: files){
                if(file.contains(".zip")){
                    entitysAll.addAll(Unzip(file));
                }
                else if(file.contains(".csv")){
                    entitysAll.addAll(ParseCsv(new FileInputStream(file), file));
                }
            }

            //получаем список объектов Entity, все нужные данные есть в полях объекта
            ArrayList<Entity> list = rezultCounter(entitysAll);

            //создаем список марок для отчета по типу 2 (заглушка)
            ArrayList<String[]> listForReportTwo = new ArrayList<>();
            for (Entity ent:list) {
                listForReportTwo.add(new String[]{ent.getName(), null});
                listForReportTwo.add(new String[]{ent.getName().toUpperCase(), null});
            }

            reportToJsonOne(list, "Report01.json");
            reportToJsonTwo(listForReportTwo, list,"Report02.json");
            reportToJsonThree(list, "Report03.json");
        }
        else
            System.out.println("No files .zip or .csv found");
    }




}
