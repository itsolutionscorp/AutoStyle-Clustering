def word_count(myword):
    worddict = {}
    for i in myword.split():
        if i in worddict:
            worddict[i] = worddict[i] + 1
        else:
            worddict[i] = 1
    return worddict
