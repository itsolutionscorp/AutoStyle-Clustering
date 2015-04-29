def word_count(words):
    wlist = {}
    for word in words.split():
        if wlist.has_key(word) :
            wlist[word] +=1 
        else:
            wlist[word] = 1
    return wlist
