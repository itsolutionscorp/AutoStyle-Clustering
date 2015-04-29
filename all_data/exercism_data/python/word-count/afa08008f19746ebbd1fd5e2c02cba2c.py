def word_count(sentence):
    counts = {}
    for w in sentence.split():
        if w not in counts:
            counts[w] = 1
        else:
            counts[w] += 1
    return counts
