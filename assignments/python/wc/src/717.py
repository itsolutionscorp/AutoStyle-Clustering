def word_count(what):
    splitarr = what.split()
    retdict = dict.fromkeys(splitarr, 0)
    for word in splitarr:
        retdict[word] += 1
    return retdict
