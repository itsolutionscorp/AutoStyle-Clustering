def word_count(phrase):
    explode = phrase.split()
    wordList = {}
    for word in explode:
        if word not in wordList.keys():
            wordList[word] = 1
        else:
            wordList[word] += 1
    return wordList
