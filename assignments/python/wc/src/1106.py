__author__ = 'Dalton'
import re
def word_count(sentence):
    sentence = re.sub('\W', ' ', sentence.lower())
    sentence = sentence.split()
    counter = {}
    for i in sentence:
        if i not in counter:
            counter[i] = 1
        elif i in counter:
            counter[i] += 1
    return counter
