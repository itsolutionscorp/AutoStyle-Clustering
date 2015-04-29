def word_count( words ):
    
    wordList = words.split()
    counter = {}

    for word in wordList:
        if word in counter:
            counter[word] += 1
        else:
            counter[word] = 1

    return counter
