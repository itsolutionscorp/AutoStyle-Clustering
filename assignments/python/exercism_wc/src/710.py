def word_count(text):
    r = {}
    for word in text.split():
        r[word] = r.get(word, 0) + 1
    return r
