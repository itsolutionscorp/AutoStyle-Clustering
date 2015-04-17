def word_count(phrase):
    counts = dict()
    for word in phrase.split():
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
