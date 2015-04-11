def detect_anagrams(word, possible_anagrams):
   
    def is_anagram(w):
        is_same_word = w.lower() == word.lower()
        if is_same_word: return False

        return sorted(w.lower()) == sorted(word.lower())
        
    return list(filter(is_anagram, possible_anagrams))
