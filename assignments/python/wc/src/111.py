def word_count(line):
    counts = {}
    for word in line.split():
        if word not in counts:
            counts[word] = 0
        counts[word] += 1
    return counts
