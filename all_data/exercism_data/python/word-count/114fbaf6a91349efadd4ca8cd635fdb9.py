#!/usr/bin/env python

def word_count(phrase):
    """ Counts the occurrences of each word in a phrase.

    Args:
        phrase (str): The given phrase

    Returns:
        A dictionary of words and the number of times they occur in the phrase.
    """
    word_dict = {}

    for word in phrase.split():
        word_dict[word] = word_dict.get(word, 0) + 1

    return word_dict
