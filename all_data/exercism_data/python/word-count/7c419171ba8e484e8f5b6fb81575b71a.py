#!/usr/bin/env python
""" exercism.io wordcount application.
"""

def word_count(input_string):
    """ Strips input of special characters and capitalization;
        Returns a dictionary of words:count
    """
    stripped_string = ""
    for letter in input_string.lower():
        if letter.isalnum() or letter.isspace():
            stripped_string += letter

    word_array = stripped_string.split(" ")
    # Remove empty values
    word_array_filtered = filter(None, word_array)
    # build dictionary
    word_count_dict = {}
    for word in word_array_filtered:
        if not word in word_count_dict:
            word_count_dict[word] = 1
        else:
            word_count_dict[word] += 1

    return word_count_dict
