def word_count(sent):
    words = {}
    for w in sent.split():
        if w not in words:
            words[w] = sent.split().count(w)
    return words
