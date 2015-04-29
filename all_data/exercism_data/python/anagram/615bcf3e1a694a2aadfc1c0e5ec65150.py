from itertools import permutations

def detect_anagrams(word, word_list):
    word = word.lower()
    variants = list(permutations(word))
    
    return [anagram for anagram in word_list 
                if  word != anagram.lower() and \
                    len(word) == len(anagram) and \
                    tuple(anagram.lower()) in variants]
