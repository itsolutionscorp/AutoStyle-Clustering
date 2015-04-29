# -*- coding: utf-8 -*-
#
# from collections import Counter


def word_count(text):

    wordlist = text.split()
    dictionary = {word: wordlist.count(word) for word in wordlist}

    # easy alternative:
    # dictionary = Counter(wordlist)

    return dictionary
