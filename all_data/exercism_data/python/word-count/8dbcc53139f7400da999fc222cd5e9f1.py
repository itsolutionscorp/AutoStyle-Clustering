# -*- coding: utf-8 -*-


def word_count(phrase):
    '''
    Given a phrase, count the occurrences of each word.
    '''
    phrase = phrase.split()
    words = {}
    for word in phrase:
        word = word.strip('!@#$%^&*():,').lower()
        if word == '':
            continue
        if word not in words:
            words[word] = 1
        else:
            words[word] = words[word] + 1
    return words
