def word_count(words):
    counts = {}
    for word in words.split():
        if word in counts:
            counts[ word ] += 1
        else:
            counts[ word ] = 1
    return counts
