__author__ = 'Chris'
from collections import Counter
sentence = "olly olly in come free"
def word_count(sentence):
    words = sentence.split(' ')
    x = Counter(words)
    for word in x:
        print('%s:' % word, x[word])
word_count(sentence)
