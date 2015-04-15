from collections import Counter

def word_count(sentence):
    counts = Counter()
    for word in sentence.split():
        counts[word] += 1
    return counts
