def word_count(words):
    counts = {}
    for word in words.replace('\n', ' ').split(' '):
        if len(word) > 0:
            if word in counts:
                counts[word] += 1
            else:
                counts[word] = 1
    return counts
