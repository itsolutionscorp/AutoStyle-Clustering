#!/usr/bin/python3


from string import punctuation


def clean(phrase):
    """
    Returns string cleaned from punctuation marks
    """
    return ''.join([char for char in phrase if char not in punctuation])


def word_count(phrase):
    """
    Returns dictionary with counts of words in phrase
    """
    phrase = clean(phrase).lower()
    hgram = {}
    for word in phrase.split():
        hgram[word] = hgram.get(word, 0) + 1
    return hgram
