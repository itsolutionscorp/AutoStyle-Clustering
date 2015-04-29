def word_count(words):
    wc = {}
    for word in words.split():
        if word in wc:
            wc[word] += 1
        else:
            wc[word] = 1
    return wc
