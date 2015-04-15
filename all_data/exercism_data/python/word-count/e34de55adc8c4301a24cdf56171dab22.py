#!/usr/bin/env python


def word_count(words):
    """
    Counts the occurrences of each word in a string and returns a dict with 'word': count pairs
    """
    list_words = words.split()  # splits on whitespace
    return_dict = {}

    for word in list_words:
        return_dict[word] = list_words.count(word)

    return return_dict
