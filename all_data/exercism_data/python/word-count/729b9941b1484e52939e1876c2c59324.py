# Python Exercism #3 Wordcount

from collections import defaultdict

def word_count(input):
    phrases = input.splitlines()
    words = []
    for phrase in phrases:
        for word in phrase.strip().split(' '):
            if word:
                words.append(word)
    d = defaultdict(int)
    for word in words:
        d[word] += 1
    return d
