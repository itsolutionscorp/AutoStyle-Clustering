def word_count(words):
    word_counts = {}

    for word in words.split():
        if not word in word_counts:
            word_counts[word] = 1
        else:
            word_counts[word] += 1

    return word_counts
