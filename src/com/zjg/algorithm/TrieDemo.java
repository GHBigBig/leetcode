package com.zjg.algorithm;

/**
 * @author zjg
 * @create 2020-01-11 10:43
 */
public class TrieDemo {

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
        Trie trie = new Trie();

        String[] strings = {"Trie", "insert", "search", "search", "startsWith", "insert", "search",
                "apple", "apple", "app", "app", "app", "app"};

        for (String string : strings) {
            trie.insert(string);
        }

        System.out.println("0000000000000000000000000000000000000000000000000000000");

        System.out.println(trie.search("ap"));
        System.out.println(trie.startsWith("ap"));


    }

    static class Trie {
        String val;
        Trie left;
        Trie right;

        /*
            string compare:
                先比字典顺序
                后比：
                如果传入的字符串等于此字符串，则值为0
                如果传入的字符串大于此字符串，则值为 -1
                如果传入的字符串小于于此字符串，则值为 1
         */

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        public Trie(String word) {
            this.val = word;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (this == null || this.val == null) {
                this.val = word;
            } else {
                insert(this, word);
            }
        }

        /**
         * 按 String compareTo 方法排序
         * 小于当前 val 的排左边
         * 大于排右边
         * 等于不存储
         *
         * @param node
         * @param word
         */
        private void insert(Trie node, String word) {
            if (node == null || node.val == null) {
                return;
            }
            if (node.val.compareTo(word) > 0) {
                if (node.left == null) {
                    node.left = new Trie(word);
                    return;
                } else {
                    insert(node.left, word);
                }
            } else if (node.val.compareTo(word) < 0) {
                if (node.right == null) {
                    node.right = new Trie(word);
                    return;
                } else {
                    insert(node.right, word);
                }
            } else {
                return;
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (this == null || this.val == null) {
                return false;
            } else {
                return search(this, word);
            }
        }

        private boolean search(Trie node, String word) {
            if (node.val.equals(word)) {
                return true;
            }else if (node.val.compareTo(word) > 0){
                if (node.left==null) {
                    return false;
                }else {
                    return search(node.left, word);
                }
            }else if (node.val.compareTo(word) < 0) {
                if (node.right==null) {
                    return false;
                }else {
                    return search(node.right, word);
                }
            }
            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (this == null || this.val == null) {
                return false;
            } else {
                return startsWith(this, prefix);
            }
        }

        private boolean startsWith(Trie node, String prefix) {
            if (node.val.startsWith(prefix)) {
                return true;
            }else if (node.val.compareTo(prefix) > 0){
                if (node.left==null) {
                    return false;
                }else {
                    return startsWith(node.left, prefix);
                }
            }else if (node.val.compareTo(prefix) < 0) {
                if (node.right==null) {
                    return false;
                }else {
                    return startsWith(node.right, prefix);
                }
            }
            return false;
        }
    }
}
