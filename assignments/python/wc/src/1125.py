def word_count(phrase):
    counts = {}
    for word in phrase.split():
        if word not in counts:
            counts[word] = 0
        counts[word] += 1
    return counts
