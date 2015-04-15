#!/usr/bin/python

import re
import string

def word_count(words):
    word_list = conv_string_to_list(words)
    clean_words = process_sentence(word_list)
    tally = {}
    for word in clean_words:
        if word.isalpha(): word = word.lower()
        if not any(tally):
            tally[word] = 1
        elif word in tally:
            tally[word] += 1
        else:
            tally[word]=1
    return tally

def process_sentence(word_list):
    new_list = []
    for word in word_list:
        new_list.append(strip_punctuation(word))
    return new_list

def strip_punctuation(word):
    exclude = set(string.punctuation)
    return ''.join(ch for ch in word if ch not in exclude)

def conv_string_to_list(sentence):
    return re.sub("[^\w]", " ",  sentence).split()
