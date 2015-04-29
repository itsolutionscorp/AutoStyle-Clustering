#!/usr/bin/python
import sys
def word_count(wordString):
    """ Returns the number of occurrences of words in wordString as a
    dictionary of the format: wordDict[word]=numberOfOccurrences """
    wordList = splitString(wordString)
    wordDict = {}
    for word in wordList:
        if wordDict.has_key(word):
            wordDict[word] += 1
        else:
            wordDict[word] = 1
    return wordDict

def splitString(wordString):
    """ Returns a list of the whitespace separated words in wordString with
    all punctuation characters trimmed"""
    rawWordList = wordString.split()
    wordList = []
    for word in rawWordList:
        wordList.append(word.strip(',.?!'))
    return wordList

if __name__ == "__main__"
    if  len(sys.argv)>1:
        _ = sys.argv.pop(0)
        for argument in sys.argv:
            print(splitString(argument))
    else:
        print("Not enough arguments")
