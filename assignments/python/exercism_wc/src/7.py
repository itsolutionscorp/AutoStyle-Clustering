def word_count(s):
    counts = {}
    for word in s.split():
        counts[word] = counts.get(word, 0) + 1
    return counts
