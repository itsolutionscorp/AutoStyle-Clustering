def word_count(phrase):
    res = {}
    for word in phrase.split():
        if word in res:
            res[word] = res[word] + 1
        else:
            res[word] = 1
    return res
