def word_count(phrase):
    words = phrase.split()
    d = {}
    for word in words:
        d[word] = d.get(word, 0) + 1
    return d
