import re

def word_count(sentence):

    wordCounts = {}
    wordList = []
    
    wordList = sentence.split()

    for word in wordList:
        if word != "":
            wordCounts[word] = wordList.count(word)

    return wordCounts




