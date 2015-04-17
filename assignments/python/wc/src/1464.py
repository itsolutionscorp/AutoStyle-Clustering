def word_count(string):
    tokens = filter(None, string.split())
    counts = {}
    for t in tokens:
        if t not in counts:
            counts[t] = tokens.count(t)
    return counts
