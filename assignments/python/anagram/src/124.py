from collections import Counter
def detect_anagrams(word, word_list):
    '''Given a word and a list of words to compare with, returns a sublist
    of possible anagrams.
    '''
    return [w for w in word_list
            if Counter(w.upper()) == Counter(word.upper())
            and w.upper() != word.upper() ]
