# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 11:57:41 2014
"""

def string_to_alphanum(input_string):
    return_string = input_string
    for c in input_string:
        if not c.isalnum():
            return_string = return_string.replace(c,' ')
    return return_string


def word_count(input_word):
    word_dict = {}
    for word in string_to_alphanum(input_word).lower().split():
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1

    return word_dict
