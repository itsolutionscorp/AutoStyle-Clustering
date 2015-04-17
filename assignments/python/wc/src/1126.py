def word_count(phrase):
    words = phrase.split();
    d = {}
    for i, word in enumerate(words):
        if words.index(word) == i:
            d[word] = words.count(word)
    return d
