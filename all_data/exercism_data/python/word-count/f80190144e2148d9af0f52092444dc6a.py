def word_count(sents):
    words = {}
    for word in sents.split():
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
