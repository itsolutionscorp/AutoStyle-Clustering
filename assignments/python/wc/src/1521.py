import re
def word_count(sentence):
    wordCounts = {}
    wordList = []
    wordList = re.split(" ", sentence.replace("\n"," "))
    for word in wordList:
        if word != "":
            wordCounts[word] = wordList.count(word)
    return wordCounts
