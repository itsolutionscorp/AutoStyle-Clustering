def word_count(phrase):
    tokens = phrase.split()
    counts = {}

    for token in tokens:
        if token in counts:
            counts[token] += 1
        else:
            counts[token] = 1

    return counts
