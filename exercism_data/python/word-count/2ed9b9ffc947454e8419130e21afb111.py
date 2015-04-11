def word_count(x):
    wordDict = {}
    wordList = []
    for word in x.split():
        wordList.append(word)
    for i in set(wordList):
        wordDict[i] = wordList.count(i)
    return wordDict
