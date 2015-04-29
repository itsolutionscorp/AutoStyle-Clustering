def word_count(phrase):
    counts = {}
    for word in phrase.split():
        counts[word] = counts[word] + 1 if word in counts.keys() else 1
    return counts
