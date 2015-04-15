# coding=utf-8
__author__ = 'jimblackler'

from collections import Counter


def word_count(words):
    """ Given a phrase counts the occurrences of each word in that phrase.

    :param words: The phrase.
    :return: A dictionary containing the occurrences of each word in that
    phrase.
    """
    return Counter(words.split())
