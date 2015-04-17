# -*- coding: utf-8 -*-
"""
Created on Sun Oct 05 19:33:41 2014
@author: Dan
"""
def detect_anagrams(word, lists):  #define function
    wordsort = sorted(word.lower())   #create a sorted word to check against, needs to be lower case to ensure case insensitivity
    sort = [sorted(i.lower()) for i in lists] #create a list of sorted lower case words from the sentence
    anagrams = []
    for i,j in zip(sort, lists):  #zip the sorted words and the list
        if word.lower() != j.lower():  #only continue if the lower case versions of words are not equal
            if wordsort == i:  #then if the sorted word is equal to a sorted element in the sentence
                anagrams.append(j)  #append to the list
    return anagrams
    
