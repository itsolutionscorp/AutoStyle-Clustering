"""
Created on Wed Sep 24 14:13:06 2014
@author: Blair
"""
import string
def word_count(sentence):
    finalCount = {}
    sentence = sentence.lower()
    sentence = sentence.translate(None, string.punctuation)
    wordList = sentence.split() #list of all words in the sentence, including repeated words
    noRepsWordList = sorted(wordList) #list of all words in the sentence with repeated words removed
    for word in noRepsWordList:
        if noRepsWordList.count(word) > 1:
            noRepsWordList.remove(word)
        finalCount[word] = wordList.count(word)
    return finalCount
