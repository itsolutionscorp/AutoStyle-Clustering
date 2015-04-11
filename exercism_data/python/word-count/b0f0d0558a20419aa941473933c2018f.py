def word_count(phrase):
    word_counts = {}
    for word in phrase.split():
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1
    return word_counts
