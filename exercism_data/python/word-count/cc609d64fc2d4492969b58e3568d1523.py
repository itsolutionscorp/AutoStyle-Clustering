__author__ = 'john'
import re


def word_count(sentence):
    word_counts = {}
    words = re.split(r'(\s|\.| |)+', sentence, re.UNICODE)
    words = [x.strip() for x in words if x != '']
    for word in words:
        if word not in word_counts.keys():
            word_counts[word] = 1
        else:
            word_counts[word] += 1
    return word_counts
