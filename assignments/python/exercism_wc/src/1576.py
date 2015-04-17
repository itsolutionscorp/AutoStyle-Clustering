def word_count(string):
    count = {}
    wordList = string.split()
    for word in wordList:
        if word not in count.keys():
            count[word] = 1
        else:
            count[word] += 1
    return count
