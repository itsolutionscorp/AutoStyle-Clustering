def word_count(phrase):
    counts = {}
    words = phrase.split()
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
