#!/usr/bin/env python

def word_count(phrase):
    """ Counts the occurrences of each word in a phrase.

    Args:
        phrase (str): The given phrase

    Returns:
        A dictionary of words and the number of times they occur in the phrase.
    """
    word_dict = {}

    words = phrase.split()
    for word in words:
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1

    return word_dict
