__author__ = 'lene'

def word_count(phrase):
    words = phrase.split()
    return { word: words.count(word) for word in words }
