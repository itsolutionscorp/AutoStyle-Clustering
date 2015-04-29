"""
Implementation of wordcount

Specification:
    split a string into words with whitespace as the separator
    count the number of occurences of each word
"""

def word_count(phrase):
    """ return a dictionary of word:count pairs """
    w_counts = {}
    word_list = phrase.split()
    for word in word_list:
        if word in w_counts:
            w_counts[word] = w_counts[word] + 1
        else:
            w_counts[word] = 1
    return w_counts
