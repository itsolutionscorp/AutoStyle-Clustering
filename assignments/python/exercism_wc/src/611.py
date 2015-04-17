"""
    wordcount.py
    count occurences of words in a string.
"""
import string
def word_count(str):
    """ docstring here """
    words = {}
    for word in str.split():
        word = word.lower()
        words[word] = words.get(word, 0) + 1
    return words
