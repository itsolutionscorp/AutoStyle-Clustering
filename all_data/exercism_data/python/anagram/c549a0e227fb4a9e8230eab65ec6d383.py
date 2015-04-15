import string

def detect_anagrams(word, anagrams):
    def s_word(w):
        return sorted(w.lower())
    
    def is_anagram(w1, w2):
        if w1.lower() != w2.lower():
            return s_word(w1) == s_word(w2)
    
    return [w for w in anagrams if is_anagram(word, w)]

name = 'thomas'

print sorted(name)
