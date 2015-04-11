#!/usr/bin/python
# -*- coding: UTF-8 -*-

import string



def word_count(sentence):
    if isinstance(sentence, str):
        sentence = str.lower(sentence)
    list_of_words = {}
    split = sentence.split(" ")
    for word in split:
        if str.isalnum(word) == False:
            new_word = ""
            for letter in word:
                if letter in string.ascii_letters or letter in string.digits:    
                    new_word += letter
            if new_word == "":
                pass
            elif new_word in list_of_words:
                list_of_words[new_word] += 1
            else:
                list_of_words.update({new_word:1})
        elif word in list_of_words:
            list_of_words[word] += 1
        else:
            list_of_words.update({word:1})
    return list_of_words
    
    
