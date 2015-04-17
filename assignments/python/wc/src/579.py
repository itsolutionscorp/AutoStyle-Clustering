'''
Created on Oct 1, 2014
@author: jbarni00
'''
import collections
def word_count(sentence):
    wordarray  = sentence.split()
    worddict = collections.Counter()
    worddict.update(wordarray)
    return worddict
