# -*- coding: utf-8 -*-

def word_count(a_string):
    words = a_string.split()
    worddict = {}
    for word in list(set(words)):
        worddict[word] = words.count(word)
    return worddict
