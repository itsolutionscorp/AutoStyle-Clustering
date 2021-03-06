def word_count(sentence):
    word_counts = {}
    words = sentence.split()
    for word in words:
        if word_counts.has_key(word):
            word_counts[word] += 1
        else:
            word_counts[word] = 1
    return word_counts
