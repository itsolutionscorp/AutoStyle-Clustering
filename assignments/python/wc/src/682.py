def word_count(sentence):
    if not sentence:
        return ''
    words = sentence.split()
    word_counts = {}
    for word in words:
        if word not in word_counts:
            word_counts[word] = 1
        else:
            word_counts[word] += 1
    return word_counts
