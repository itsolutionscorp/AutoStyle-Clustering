def word_count(phrase):
    word_set = set(phrase.split())
    w_counts = {}
    for w in word_set:
        w_counts[w] = 0
    for w in phrase.split():
        if w in word_set:
            w_counts[w] += 1
    return w_counts
