from collections import Counter
def detect_anagrams(word, word_list):
    if word in word_list:
        return []
    word_c = Counter(word.lower())
    word_list_counters = dict(zip(word_list, (Counter(w.lower()) for w in word_list)))
    anagrams = [w for w in word_list_counters if (word_list_counters[w] == word_c) and (w != word)]
    anagrams.reverse()
    return anagrams
    
        
