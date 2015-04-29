# -*- coding: utf-8 -*-
"""
Created on Fri Sep 26 10:11:42 2014
@author: Blair
"""
def detect_anagrams(word, candidateList):
    anagramList = []
    letterDict = {}
    for letter in word.lower():
        if letter not in letterDict:
            letterDict[letter] = word.lower().count(letter)
    for candidate in candidateList:
        possible = True
        if len(candidate) != len(word) or candidate.lower() == word.lower():
            #if it's the same word or not the same length, then go ahead and skip it
            possible = False
        for letter in candidate.lower():
            if candidate.lower().count(letter) != letterDict.get(letter):
                possible = False
                    
        if possible == True:
            anagramList.append(candidate)
            
    return anagramList
