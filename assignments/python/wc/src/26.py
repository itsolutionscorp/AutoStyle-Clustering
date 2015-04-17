"""
Created on Wed Sep 24 01:05:26 2014
@author: laegrim
"""
import re
def word_count(phrase):
    '''
    Return a dictionary containing the individual words and their given 
    frequency counts in a phrase
    '''
    words = re.sub('[^\s\w]', '', phrase).lower().split() 
    return dict(zip(words, [words.count(word) for word in words]))
