# -*- coding: utf-8 -*-
import string


def word_count(phrase):
    '''
    Given a phrase, count the occurrences of each word.
    '''
    word_count_dictonary = {}
    # Loop through each word in the phrase:
    for word in phrase.split():
        # Strip punctuation and lower capitalized chars:
        word = word.strip(string.punctuation).lower()
        if word == '':
            continue
        # Add new word:
        if word not in word_count_dictonary:
            word_count_dictonary[word] = 1
        # Increment word already in dictonary:
        else:
            word_count_dictonary[word] = word_count_dictonary[word] + 1
    return word_count_dictonary
