def word_count(words):
    words.replace('\n', ' ')
    words = words.replace('\n', ' ').split(' ')
    wdict = {}
    for w in words:
        if w:
            wdict[w] = words.count(w)
    return wdict
