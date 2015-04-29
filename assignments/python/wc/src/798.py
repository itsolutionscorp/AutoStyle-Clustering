def word_count(instring):
    worddict = {}
    strlist = str.split(instring)
    for st in strlist:
        if st not in worddict.keys():
            worddict[st] = 1
        else:
            worddict[st] += 1
    return worddict
