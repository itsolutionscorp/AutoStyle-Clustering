def word_count(sentence):
    word_counts = {}
    for word in sentence.split():
        word_counts[word] = word_counts.get(word, 0) + 1
    return word_counts
