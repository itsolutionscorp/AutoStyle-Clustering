def detect_anagrams(word, word_list):
    word = word.lower()
    sorted_word = ''.join(sorted(word))
    results = []
    for w in word_list:        
        if len(w) == len(word) and word != w.lower():
            sorted_w = ''.join(sorted(w.lower()))
            if sorted_w == sorted_word:
                results.append(w)
    return results
        
        
