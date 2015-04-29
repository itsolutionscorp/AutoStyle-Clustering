def word_count(phrase):
    d = dict()
    for word in phrase.strip().split():
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
    return d
