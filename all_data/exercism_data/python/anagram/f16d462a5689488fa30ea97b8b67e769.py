from collections import Counter

def is_anagram(word1, word2):
    word1, word2 = word1.lower(), word2.lower()
    return Counter(word1) == Counter(word2) and word1 != word2

def detect_anagrams(word, possible_anagrams):
    return [choice for choice in possible_anagrams if is_anagram(word, choice)]
