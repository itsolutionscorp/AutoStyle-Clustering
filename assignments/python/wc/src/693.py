def word_count(phrase):
    wordDict = {}
    for word in set(phrase.split()):
        wordDict[word] = phrase.split().count(word)
    return wordDict
