"""A simple function which takes a block of text, and gives back a
dictionary of unique words, and a count of how many times the word
appears in the text"""
from collections import Counter
def word_count(phrase):
    """ String => Dictionary of Token:appearance_count pairs"""
    return dict(Counter(phrase.split()))
