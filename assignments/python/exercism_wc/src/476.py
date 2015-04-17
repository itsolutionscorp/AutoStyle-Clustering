from collections import Counter
def word_count(words):
    counts = Counter()
    for word in words.split():
        if len(word) > 0:
            counts[word] += 1
    return dict(counts)
