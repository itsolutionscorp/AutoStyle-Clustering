"""
Created on Sun Oct 12 21:23:55 2014
@author: Home Base
"""
def word_count(sentence):
    wordList=sentence.split()
    wordDict={} #initilize word counter
    for word in wordList:
        if word not in wordDict: #if the word is not already in the dictionary
            wordDict[word]=wordList.count(word) #add the word to the dictionary with the value being the count
    return wordDict #return the dictionary
