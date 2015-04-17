def word_count(sentence):
    trackWords = {}
    for word in sentence.split():
        if word not in trackWords:
            trackWords[word] = 1
        else:
            trackWords[word] += 1
    return trackWords
