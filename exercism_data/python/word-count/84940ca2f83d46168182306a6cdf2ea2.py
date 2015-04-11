'''
Created on Oct 1, 2014

@author: jbarni00
'''

def word_count(sentence):
    wordarray  = sentence.split()
    worddict = {}

    for i in wordarray:
        if i in worddict:
            worddict[i] = worddict[i] + 1
        else:
            worddict[i] = 1
    return worddict
