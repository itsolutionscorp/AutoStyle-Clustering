def word_count(phrase):
    phrase = phrase.strip().split()
    counts = {}
    for word in phrase:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
