__author__ = 'js'

from collections import Counter

def word_count(sentence):
    # breaks string into list of words so that it is
    # in the proper format for Counter()
    wordList = sentence.split()
    # returns number of time element is repeated along with name of element
    return Counter(wordList)
