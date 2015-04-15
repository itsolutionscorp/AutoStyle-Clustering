def word_count(string):
    words = string.split()
    counts = {}
    for word in words:
        if not word in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
