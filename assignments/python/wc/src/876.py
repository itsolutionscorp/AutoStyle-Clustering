def word_count(words):
    d = {}
    for word in words.split():
        if d.get(word):
            d[word] += 1
        else:
            d[word] = 1
    return d
