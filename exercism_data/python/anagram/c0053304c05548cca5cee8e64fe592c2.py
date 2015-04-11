"""
This file, anagram.py, contains two functions.  These functions are used to
detect anagrams. View functions for in depth details on what each does.

This exercise is for Exercism.io
"""

def getLetterDistribution(w):
    """
    Takes in a one word string, w, and returns a dictionary containing
    the counts of each letter in w.
    """
    letterList = {}
    for l in w:
        letterList[l.lower()] = w.count(l)
    return letterList

def detect_anagrams(word, possibleAnagrams):
    """
    Takes in a one words string, word, and a list of one word strings,
    possibleAnagrams. Checks to see if any of the words in possibleAnagrams
    is an anagram of word. If it is an anagram, it gets added to a list of
    anagrams.  The function returns the list of anagrams.
    """
    letterList = getLetterDistribution(word)
    anagramList = []
    for a in possibleAnagrams:
        if (a.lower() == word.lower()): #a word is not an anagram of itself
            continue
        elif (getLetterDistribution(a) == letterList):
            anagramList.append(a)
    return anagramList
