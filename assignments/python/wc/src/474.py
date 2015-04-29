def word_count(phrase):
    words = phrase.split()
    counts = {}
    for word in words:
        if word in counts.keys():
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
