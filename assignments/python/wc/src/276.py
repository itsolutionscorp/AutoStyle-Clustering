def word_count(phrase):
    clean_phrase = phrase.strip('\n')
    splitted_phrase = clean_phrase.split()
    finaldict = {}
    for w in splitted_phrase:
        if w in finaldict:
            finaldict[w] += 1
        else:
            finaldict[w] = 1
    return finaldict
