__author__ = 'js'
from collections import Counter
def word_count(sentence):
    wordList = sentence.split()
    return Counter(wordList)
