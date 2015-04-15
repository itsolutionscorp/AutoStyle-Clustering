# wordcount.py
from collections import Counter

def word_count(phrase):
    """ Returns the number of words in 'phrase' """
    
    # Replace all newline characters with spaces and
    # split 'phrase' into words to feed into the Counter
    words = Counter(phrase.replace('\n', ' ').split(' '))

    # Check if empty strings have been counted and delete them from 'words'
    if '' in words.keys():
        del words['']

    # Return the dictionary of words
    return words
