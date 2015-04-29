def word_count(S):
    counts = {}
    for word in S.split():
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
