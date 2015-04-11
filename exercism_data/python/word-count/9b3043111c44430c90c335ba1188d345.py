from collections import defaultdict

def count(words):
    counts = defaultdict(lambda: 0)
    for word in words:
        counts[word] += 1
    return dict(counts)

def word_count(phrase):
    words = phrase.split()
    counts = count(words)
    return counts
