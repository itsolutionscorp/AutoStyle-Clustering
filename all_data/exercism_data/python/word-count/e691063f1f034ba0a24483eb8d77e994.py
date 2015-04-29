'''
Created on Oct 1, 2014

@author: jbarni00
'''
import collections

def word_count(sentence):
    return collections.Counter(sentence.split())
