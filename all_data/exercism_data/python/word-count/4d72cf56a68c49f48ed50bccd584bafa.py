# -*- coding: utf-8 -*-
"""
Created on Mon Oct 06 18:00:48 2014

@author: klin
"""

def word_count(word):
    table = dict(); 
    tokens = word.split();
    for w in tokens: 
        table[w] = table.get(w,0) + 1
    return table
    

print word_count("test1 test2 test1")
