def word_count(words):
    wordCount = {}
    wordsList = words.split()
    for word in wordsList:
        if(word not in wordCount):
            wordCount[word] = 0
        wordCount[word] += 1
    return(wordCount)
