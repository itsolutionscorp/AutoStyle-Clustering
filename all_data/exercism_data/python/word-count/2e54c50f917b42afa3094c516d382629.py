def word_count( words):

    words = words.split()
    counts  = {}

    for word in words:
        if word not in counts.keys():
            counts[word] = 1
        else:
            counts[word] += 1

    return counts
