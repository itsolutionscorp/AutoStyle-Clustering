def word_count(sentence):
    counts = {}
    words = sentence.split()

    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1

    return counts
