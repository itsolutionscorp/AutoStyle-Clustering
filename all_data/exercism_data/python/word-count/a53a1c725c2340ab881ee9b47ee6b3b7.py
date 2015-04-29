#!/usr/bin/python3.4


def word_count(sentence):
    words = sentence.split()

    words_count = {}
    for word in words:
        if words_count.__contains__(word):
            words_count[word] += 1
        else:
            words_count[word] = 1

    return words_count
