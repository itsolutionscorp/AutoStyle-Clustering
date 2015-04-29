from collections import Counter
def word_count(input):
    counts = Counter()
    for word in input.split():
        counts[word] += 1
    return counts
