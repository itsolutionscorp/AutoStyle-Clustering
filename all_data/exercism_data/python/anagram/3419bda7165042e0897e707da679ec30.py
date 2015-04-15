import string

def detect_anagrams(word, possibles):
    return [x for x in possibles if is_anagram(word, x)]

def is_anagram(word1, word2):
    lower1 = string.lower(word1)
    lower2 = string.lower(word2)
    
    return count_letters(lower1) == count_letters(lower2) and lower1 != lower2
    
    
def count_letters(word):
    d = dict()
    for c in word:
        d[c] = d.get(c, 0) + 1
        
    return d
