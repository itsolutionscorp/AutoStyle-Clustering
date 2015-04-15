from collections import Counter

def detect_anagrams(word, anagram_candidates):
    anagram_counter = Counter(word.lower())
    return [anagram for anagram in anagram_candidates if
        anagram_counter == Counter(anagram.lower()) and word.lower() != anagram.lower()]
