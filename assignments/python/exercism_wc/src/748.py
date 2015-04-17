import re
def word_count(sentence):
    wordCounts = {}
    wordList = sentence.split()
    for word in wordList:
        wordCounts[word] = wordList.count(word)
    return wordCounts
