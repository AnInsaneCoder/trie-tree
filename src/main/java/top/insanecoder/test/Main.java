package top.insanecoder.test;

import top.insanecoder.data_struct.TrieTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Author: shaohang.zsh
 * Date: 2016/7/25
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\words"));
        TrieTree trieTree = new TrieTree();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            trieTree.insert(line);
        }

        Map<String, Integer> map = trieTree.getWordsForPrefix("go");
//        Map<String, Integer> map = trieTree.getAllWords();
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println("total num of go : " + trieTree.getNumOfPrefix("go"));
    }
}
