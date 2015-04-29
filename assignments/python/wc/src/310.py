def word_count(phrase):
    words = phrase.split()
    wordcount = {}
    for w in words:
        if not w in wordcount:
            wordcount[w] = 1
        else:
            wordcount[w] += 1
    return wordcount
