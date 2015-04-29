def word_count(string):
    stringInPieces = string.split()
    worddict = {}
    for word in stringInPieces:
        if word in worddict:
            worddict[word] += 1
        else:
            worddict[word] = 1
        return worddict
