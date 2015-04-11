def detect_anagrams(word, word_list):
    sort = ''.join(sorted(word.lower()))
    return [w for w in word_list if ''.join(sorted(w.lower())) == sort and w.lower() != word.lower()]
        
