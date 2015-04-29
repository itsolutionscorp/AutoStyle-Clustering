# -*- coding: utf-8 -*-
import re

def word_count(source):
    dict = {}
    words = re.sub(' +', ' ', source).replace('\n',' ').split(' ')
    for word in words:
        if dict.has_key(word):
            dict[word] += 1
        else:
            dict[word] = 1
    return dict
