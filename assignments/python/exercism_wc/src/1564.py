from re import split
from collections import Counter
def word_count(phrase):
    counter = Counter()
    for word in split('\W+', phrase.lower()):
        if word == '' : continue
        counter[word] += 1
    return counter
