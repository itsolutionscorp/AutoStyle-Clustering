def word_count(message):
    wordDict = {}
    wordsList = message.split()
    for word in wordsList:
        wordDict[word] = wordsList.count(word)
    return wordDict
