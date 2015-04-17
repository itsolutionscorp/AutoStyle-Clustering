def detect_anagrams(word, word_list):
    sort = sorted(word.lower())
    return [w for w in word_list if sorted(w.lower()) == sort and w.lower() != word.lower()]
        
