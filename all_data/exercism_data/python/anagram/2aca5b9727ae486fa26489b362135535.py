from collections import Counter

def detect_anagrams(word, word_list):
    word_c = Counter(word.lower())
    return [w for w in word_list if (Counter(w.lower()) == word_c) and (w.lower() != word)]
    
        



