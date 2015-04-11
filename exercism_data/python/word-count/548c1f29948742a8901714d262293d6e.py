def word_count(phrase):
    out = dict()
    for word in phrase.rstrip().split():
        if word in out:
            out[word] = out[word] + 1
        else:
            out[word] = 1
    return out
