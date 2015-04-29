# Python Exercism #3 Wordcount

from collections import defaultdict

def word_count(input):
    phrases = input.split()
    d = defaultdict(int)
    
    for word in phrases:
        d[word] += 1
    return d
