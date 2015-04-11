from collections import Counter


def is_anagram(word1, word2):
    word1, word2 = [word1.lower(), word2.lower()]
    if word1 == word2:
        return False
    return Counter(word1) == Counter(word2)


def detect_anagrams(word, anagram_list):
    return [anagram for anagram in anagram_list if is_anagram(word, anagram)]
