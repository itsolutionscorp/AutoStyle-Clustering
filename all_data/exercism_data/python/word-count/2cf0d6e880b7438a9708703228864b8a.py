def word_count(phrase):
    res = {}
    words = phrase.split()
    for word in words:
        seen = res.get(word, 0)
        res[word] = seen + 1

    return res
