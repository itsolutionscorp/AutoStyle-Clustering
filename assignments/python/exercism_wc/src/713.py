def word_count(phrase):
    counts = {}
    words = phrase.split()
    for x in set(words):
        counts[x] = words.count(x)
    return counts