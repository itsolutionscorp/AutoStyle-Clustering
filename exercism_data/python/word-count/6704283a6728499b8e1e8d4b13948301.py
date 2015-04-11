"""
    wordcount.py
    count occurences of words in a string.
"""

import string

def word_count(str):
    """ docstring here """
    # str = str.lower()
    # delete_table = string.maketrans(string.ascii_lowercase, ' ' * len(string.ascii_lowercase))
    # str.translate(None, delete_table)
    words = {}
    for word in str.split():
        word = word.lower()
        words[word] = words.get(word, 0) + 1

    return words
