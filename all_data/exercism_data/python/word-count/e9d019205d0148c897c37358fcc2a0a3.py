def word_count(string):

    words = string.strip().split()
    wordMap = {}
    for word in words:
        if word not in wordMap:
            wordMap[word] = 1
        else:
            wordMap[word] += 1
    return wordMap
