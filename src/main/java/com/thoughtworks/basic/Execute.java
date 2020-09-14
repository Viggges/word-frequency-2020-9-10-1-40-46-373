package com.thoughtworks.basic;

import java.io.*;
import java.util.*;

/**
 * description: Execute <br>
 * date: 2020/9/14/014 10:39 <br>
 *
 * @author: LouWei <br>
 * version: 1.0 <br>
 */
public class Execute {


    public static void main(String[] args) throws IOException {
        String prideString = readTxt("C:\\Users\\Administrator\\Desktop\\pride-and-prejudice.txt");
        String stopString = readTxt("C:\\Users\\Administrator\\Desktop\\stop-words.txt");
        List<String> stopList = Arrays.asList(stopString.split(","));
        String[] prideList = prideString.replaceAll("[^a-zA-Z]", " ").split(" ");
        //方法1
        Map<String, Integer> freMap = getFrequency(prideList, stopList);
        List<Map.Entry<String, Integer>> freList = new ArrayList<>(freMap.entrySet());
        freList = getSortResult(freList);
        print(freList);
        //方法2
        List<FrequencyWord> frequencyWordList = getFrequency2(prideList, stopList);
        List<FrequencyWord> frequencyWordSortList = getSortResult2(frequencyWordList);
        print2(frequencyWordSortList);

    }

    private static void print2(List<FrequencyWord> freList) {
        for (FrequencyWord frequencyWord : freList) {
            System.out.println(frequencyWord.getWord() + "-" + frequencyWord.getCount());
        }
    }

    private static List<FrequencyWord> getSortResult2(List<FrequencyWord> frequencyWordList) {
        Collections.sort(frequencyWordList, (o1, o2) -> (o2.getCount().compareTo(o1.getCount())));
        return frequencyWordList.subList(0, 25);
    }

    private static List<FrequencyWord> getFrequency2(String[] prideList, List<String> stopList) {
        List<FrequencyWord> result = new ArrayList<>();
        for (String str : prideList) {
            str = str.toLowerCase();
            if (stopList.contains(str) || str.length() < 2) {
                continue;
            }
            int count = Collections.frequency(Arrays.asList(prideList), str);
            FrequencyWord frequencyWord = new FrequencyWord(str, count);
            result.add(frequencyWord);
        }
        return result;
    }

    private static List<Map.Entry<String, Integer>> getSortResult(List<Map.Entry<String, Integer>> freList) {
        Collections.sort(freList, (o1, o2) -> (o2.getValue().compareTo(o1.getValue())));
        return freList.subList(0, 25);
    }

    private static void print(List<Map.Entry<String, Integer>> freList) {
        for (Map.Entry<String, Integer> entry : freList) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    private static Map<String, Integer> getFrequency(String[] prideList, List<String> stopList) {
        Map<String, Integer> result = new HashMap<>();
        for (String str : prideList) {
            str = str.toLowerCase();
            if (stopList.contains(str) || str.length() < 2) {
                continue;
            }
            if (result.containsKey(str)) {
                result.put(str, result.get(str) + 1);
            } else {
                result.put(str, 1);
            }
        }
        return result;
    }

    public static String readTxt(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        StringBuffer buffer = new StringBuffer();
        while (line != null) {
            buffer.append(line);
            buffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
        return buffer.toString();
    }
}
