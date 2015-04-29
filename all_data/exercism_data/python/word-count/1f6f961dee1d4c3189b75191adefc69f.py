# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 20:37:43 2014

@author: luis
"""
from collections import Counter

def word_count(sentence):
    return Counter(sentence.split())
    
