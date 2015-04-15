def count_letters(string):
        counts = {}
        for a in string.lower():
            counts[a] = counts.get(a, 0) + 1
        return counts

def detect_anagrams(word, word_list):
    word_counts = count_letters(word)
    anagrams = []
    for w in word_list:
       if word_counts == count_letters(w) and word.lower() != w.lower():
            anagrams.append(w)
    return anagrams
    
