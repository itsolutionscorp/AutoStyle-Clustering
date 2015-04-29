def word_count(phrase):
    phrase = phrase.split()
    counts = {}
    for i in phrase:
        if i in counts:
            counts[i] += 1
        else:
            counts[i] = 1\

    return counts
