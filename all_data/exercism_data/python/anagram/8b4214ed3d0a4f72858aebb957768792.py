from collections import Counter

def detect_anagrams(word,options):
    return [anagram for anagram in options
            if Counter(word.lower()) == Counter(anagram.lower())
            if word.lower() != anagram.lower()]
