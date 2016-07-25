package top.insanecoder.data_struct;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: shaohang.zsh
 * Date: 2016/7/25
 */
public class TrieTree {

    private class TrieTreeNode {

        private int duplicateNum;
        private int prefixNum;
        private TrieTreeNode[] childNodes;
        private boolean isLeaf;

        public TrieTreeNode() {
            duplicateNum = 0;
            prefixNum = 0;
            childNodes = new TrieTreeNode[26];   // store the 26 characters
            isLeaf = false;
        }
    }

    private TrieTreeNode root;

    public TrieTree() {
        root = new TrieTreeNode();
    }

    public void insert(String str) {
        insert(this.root, str);
    }

    private void insert(TrieTreeNode root, String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';

            if (root.childNodes[index] == null) {
                root.childNodes[index] = new TrieTreeNode();
            }

            root.childNodes[index].prefixNum++;

            if (i == chars.length - 1) {
                root.childNodes[index].isLeaf = true;
                root.childNodes[index].duplicateNum++;
            }

            root = root.childNodes[index];
        }
    }

    public boolean isExist(String str) {
        return search(this.root, str);
    }

    private boolean search(TrieTreeNode root, String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (root.childNodes[index] == null) {
                return false;
            }

            root = root.childNodes[index];
        }
        return true;
    }

    public Map<String, Integer> getAllWords() {

        Map<String, Integer> map = new HashMap<String, Integer>();
        preTraversal(map, this.root, "");
        return map;
    }

    public Map<String, Integer> getWordsForPrefix(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieTreeNode root = this.root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (root.childNodes[index] == null) {
                return null;
            }
            root = root.childNodes[index];
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        preTraversal(map, root, prefix);
        return map;
    }

    private void preTraversal(Map<String, Integer> map, TrieTreeNode root, String prefix) {

        if (root.isLeaf) {
            map.put(prefix, root.duplicateNum);
        }

        for (int i = 0; i < root.childNodes.length; i++) {
            if (root.childNodes[i] != null) {
                char ch = (char)(i + 'a');
                preTraversal(map, root.childNodes[i], prefix + ch);
            }
        }
    }

    public int getNumOfPrefix(String prefix) {
        return getNumOfPrefix(this.root, prefix);
    }

    private int getNumOfPrefix(TrieTreeNode root, String prefix) {
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (root.childNodes[index] == null)
                return 0;

            root = root.childNodes[index];
        }

        return root.prefixNum;
    }
}
