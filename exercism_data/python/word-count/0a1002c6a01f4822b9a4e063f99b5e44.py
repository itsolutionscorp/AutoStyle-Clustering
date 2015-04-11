from collections import defaultdict

def word_count(words):
    word_counts = defaultdict(int)
    for word in words.split():
        word_counts[word] += 1
    return word_counts
