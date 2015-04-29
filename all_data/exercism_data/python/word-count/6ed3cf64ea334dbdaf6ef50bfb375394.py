import string
from collections import Counter

def word_count(phrase):
    '''count occurences of words in a phrase, ignoring case and punctuation'''
    d = Counter()
    for word in phrase.lower().split():
        word = word.strip(string.punctuation)
        if not len(word): pass
        else: d[word] += 1
    return(d)
