def word_count(string):
    counts = {}

    for s in string.split():
        counts[s] = counts.get(s, 0) + 1

    return counts
