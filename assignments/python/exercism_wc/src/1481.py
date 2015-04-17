def word_count(phrase):
    splitted_phrase = phrase.split()
    finaldict = {}
    for w in splitted_phrase:
        if w in finaldict:
            finaldict[w] += 1
        else:
            finaldict[w] = 1
    return finaldict
