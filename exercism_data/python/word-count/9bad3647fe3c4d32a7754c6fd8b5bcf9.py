#!/usr/bin/env python
# -*- coding: utf-8 -*-


def word_count(text=None):
    if text is None:
        print "You didn't type a text!"
        exit(1)
    words = text.split()
    return dict([(x, words.count(x)) for x in set(words)])


# def word_count(text=None):
#     if text is None:
#         print "You didn't type a text!"
#         exit(1)
#     text = str(text)  # if int or float
#     words = text.replace('\n', ' ').split()
#     freq = {}
#     for w in words:
#         if w in freq.keys():
#             freq[w] = freq[w] + 1
#         else:
#             freq[w] = 1
#     return freq


# def word_count2(text=None):
#     # python 3
#     from collections import Counter
#     if text is None:
#         print "You didn't type a text!"
#         exit(1)
#     text = str(text)  # if it's isnumeric
#     words = text.replace('\n', ' ').split(' ')
#     freq = {}
#     c = Counter(text)
#     for tu in c:
#         freq.update(dict(tu))
#     return freq
