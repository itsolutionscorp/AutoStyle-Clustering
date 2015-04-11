import string

def detect_anagrams(word, anagrams):
    def s_word(w):
        return sorted(list(w))
    
    def is_anagram(w1, w2):
        if w1.lower() != w2.lower():
            return s_word(w1.lower()) == s_word(w2.lower())
    
    words = []
    for w in anagrams:
        if is_anagram(w, word):
            words.append(w)
    return words
