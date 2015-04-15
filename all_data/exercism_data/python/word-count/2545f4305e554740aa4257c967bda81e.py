import collections

def word_count(phrase):
    counts = collections.defaultdict(lambda: 0)
    for word in phrase.split():
        counts[word] += 1
    return counts
