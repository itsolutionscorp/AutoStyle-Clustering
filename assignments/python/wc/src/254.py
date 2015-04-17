'''
Created on Oct 1, 2014
@author: jbarni00
'''
from collections import Counter
def word_count(sentence):
    return Counter(sentence.split())
