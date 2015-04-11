def word_count(phrase):
    words = phrase.split()
    counts = {}
    for word in words:
        counts[word] = words.count(word)
    return counts
