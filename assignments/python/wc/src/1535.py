from collections import Counter
import re
def word_count(sentence):
    wordList = sentence.split()
    return Counter(wordList)
