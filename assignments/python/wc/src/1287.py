def word_count(phrase):
    newphrase = phrase.replace('\n',' ')
    explode = newphrase.split(' ')
    wordList = {}
    for word in explode:
        if word != '':
            if word not in wordList.keys():
                wordList[word] = 1
            else:
                wordList[word] += 1
    return wordList
