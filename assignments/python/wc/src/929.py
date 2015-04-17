"""
Created on Wed Sep 24 09:39:03 2014
@author: Dan
"""
import string
def word_count(words):
    thewords = {}   #create a new dictionary
    words = words.split()  #splot the sentence up into individual words
    w = []  #create a list for individual words to go into
    for i in words:  
        i = i.lower()   #make sure the words are lower case
        w.append(''.join(x for x in i if x not in string.punctuation)) #remove all punctuation
    for i in w:   #for i in the list
        if i == '':
            w.remove('')  #remove the spaces
    for i in w:
        if i not in thewords.keys():
            thewords[i] = 1
        elif i in thewords.keys():
            thewords[i] += 1                #for each value in the list if it is in the dictionary add 1 to it's value
    return thewords
