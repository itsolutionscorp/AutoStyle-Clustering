def detect_anagrams(word, word_list):
    def is_anagram(word1, word2):
        word1 = word1.lower()
        word2 = word2.lower()
        return word1 != word2 and sorted(word1) == sorted(word2)
    return [w for w in word_list if is_anagram(word, w)]
