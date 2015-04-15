import re

def count_in(word, phrase):
    words = phrase.split()
    return len(filter(lambda w: w==word, words))

def word_count(phrase):
    return {word:count_in(word, phrase) for word in set(phrase.split())}
