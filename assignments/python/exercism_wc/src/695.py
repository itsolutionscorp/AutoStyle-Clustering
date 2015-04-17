from collections import Counter
def word_count(sentence):
    wordList = sentence.split()
    counterDict = {}
    for index, word in enumerate(wordList):
        counter = 1
        if (word in counterDict):
            continue
        for comparedWord in wordList[index+1:]:
            if(word == comparedWord):
                counter += 1
        counterDict[word]=counter
    return counterDict
