package org.csvHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Util {
    public static ArrayList<String[]> ParseCsv(InputStream stream, String name) {
        try {
            System.out.println("Try parsing file: "+name);
            ArrayList<String[]> strings = new ArrayList<>();
            //получаем поток из файла и обрабатываем построчно
            Scanner sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                String string = sc.nextLine();
                if (!string.contains("#")) { // комментарии (#) не учитываем
                    strings.add(string.split(","));
                }
            }
            return strings;
        }
        catch (Exception e){
            System.out.println("Error parsing file: "+name);
            return null;
        }
    }

    public static ArrayList Unzip(String path) throws IOException {
        ArrayList<String[]> list = new ArrayList<>();
        //получаем входной поток с файла с оберткой в обработчик ZIP
        System.out.println("Try to read..."+path);
        FileInputStream in = new FileInputStream(path);
        ZipInputStream zipIn = new ZipInputStream(in);

        ZipEntry entry = null;
        //последовательно считываем файлы из архива, и парсим в list
        while ((entry = zipIn.getNextEntry()) != null) {
            if (entry.getSize() < 1) {
                System.out.println("File is empty");
                continue;
            }
            list.addAll(ParseCsv(zipIn, entry.getName()));
            System.out.println("Parsing file "+entry+" is OK");
        }
        zipIn.close();
        return list;
    }


    //метод для перебора повторяющихся ключей в ArrayList и выборки их значений
    public static ArrayList<Entity> rezultCounter(ArrayList<String[]> strings) {
        ArrayList<Entity> entities = new ArrayList<>();
        //создаем Map и последовательно заполняем его
        Map<String, String> result = new TreeMap<>();
        for (int i = 0; i < strings.size(); i++) {
            if (!result.containsKey(strings.get(i)[0].toLowerCase())) //если нет в Map до добавляем ключ-значение (регистронезависимая проверка)
                result.put(strings.get(i)[0].toLowerCase(), strings.get(i)[1]);
            else //если есть, то конкетируем в строку
                result.replace(strings.get(i)[0].toLowerCase(), result.get(strings.get(i)[0].toLowerCase()) + "," + strings.get(i)[1]);
        }
        //перебираем Map и последовательно получаем список объектов Entity
        for(Map.Entry entry: result.entrySet()){
            entities.add(new Entity(entry.getKey().toString(), entry.getValue().toString()));
        }
        return entities;
    }

    public static String[] searchFiles(String path){
        String filePath = new File("").getAbsolutePath();
        File folder = new File(filePath+path); //path указывает на директорию

        final String[] mask = {".zip", ".csv"};
        String[] strings = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                for (String s : mask)
                    if (name.toLowerCase().endsWith(s)) return true;
                return false;
            }
        });

        for (String s: strings)
            System.out.println(s+" - file found");
        return strings;
    }

}
