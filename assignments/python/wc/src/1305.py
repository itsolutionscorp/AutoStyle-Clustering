def word_count(phrase):
    word_counts = {}
    for word in phrase.split():
        word_counts[word] = word_counts.get(word, 0) + 1
    return word_counts
