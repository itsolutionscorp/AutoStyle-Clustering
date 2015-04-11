def word_count(sentence):
    words = sentence.split()
    counts = {}
    for word in words:
        count = counts[word] if word in counts else 0
        count += 1
        counts[word] = count
    return counts
