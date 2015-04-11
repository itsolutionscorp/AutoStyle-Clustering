# -*- coding: utf-8 -*-
"""
Created on Sun Nov 02 19:52:21 2014


"""

def word_count(text):
    if type(text) == str:
        remove_lines = text.replace("\n"," ")
        words = remove_lines.split(" ")
        unique_words={}
        unique_words[words[0]]=1
        for idx_1, i in enumerate(words):
            if idx_1 > 0:
                if i in unique_words.keys():
                    unique_words[i]=unique_words[i]+1
                elif i != "":
                    unique_words[i]=1
    return unique_words
        
