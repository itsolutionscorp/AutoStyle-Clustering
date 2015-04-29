from collections import defaultdict

def word_count(words):
    counts = defaultdict(int)
    for word in words.split():
        counts[word] += 1

    return counts
