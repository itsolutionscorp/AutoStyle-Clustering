def word_count(text):

    counts = dict()

    for word in text.split():
        counts[word] = counts.get(word, 0) + 1

    return counts
