from collections import Counter

def detect_anagrams(word, candidates):
    return [x for x in candidates 
            if x.lower() != word.lower() and 
            Counter(x.lower()) == Counter(word.lower())]
