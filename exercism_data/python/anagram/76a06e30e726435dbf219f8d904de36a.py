import string
from collections import Counter

def detect_anagrams(word, possibles):
    lower_word = string.lower(word)
    return [
        x for x in possibles
        if Counter(lower_word) == Counter(string.lower(x)) 
            and lower_word != string.lower(x)
    ]
