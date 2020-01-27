package org.csvHandler;

import java.util.ArrayList;
import java.util.Collections;

public class Entity{
    private String name;
    private String value;
    private ArrayList<Integer> array;
    private int sum;

    public Entity(String name, String value){
        this.name = name.toLowerCase();
        this.value = value;
        this.array = valueToIntegerArray();
        this.sum = setSumArray();
    }

    public void setValue(String value) {
        this.value = value;
        this.array = valueToIntegerArray();
        this.sum = setSumArray();
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getArrayList() {
        return array;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.array = arrayList;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public ArrayList<Integer> valueToIntegerArray(){
        if(value != null){
            String[] integers = value.split(",");
            ArrayList<Integer> rez = new ArrayList<>();
            for(int i=0; i<integers.length; i++){
                try {
                    rez.add(Integer.parseInt(integers[i]));
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            Collections.sort(rez, Collections.reverseOrder());
            return rez;
        }
        return null;
    }

    public int setSumArray(){
        if(array.size() >0){
            int sum = 0;
            for (int i: array) {
                sum+=i;
            }
            return sum;
        }else return 0;
    }

    @Override
    public String toString(){
        return this.name + "-"+this.value;
    }


}
