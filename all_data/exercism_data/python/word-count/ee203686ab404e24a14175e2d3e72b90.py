import re


def word_count(sentence):
    words = {}

    for word in re.split('\s+', sentence):
        if word in words:
            words[word] += 1
        else:
            words[word] = 1

    return words
