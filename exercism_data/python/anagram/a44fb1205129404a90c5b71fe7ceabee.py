from itertools import permutations

## BISECTION VARIANT ##
def detect_anagrams(word, word_list):
    word = word.lower()
    variants = sorted(permutations(word))

    return [anagram for anagram in word_list 
                if  word != anagram.lower() and \
                    len(word) == len(anagram) and \
                    is_in(tuple(anagram.lower()), variants)]

def is_in(el, seq):
    """ 
    bisection-search helper
    """
    low = 0
    high = len(seq)

    while True:
        middle = (low + high) / 2

        if low == middle:
            return seq[middle] == seq
        
        if seq[middle] == el:
            return True
        
        elif seq[middle] > el:
            high = middle
        
        elif seq[middle] < el:
            low = middle
