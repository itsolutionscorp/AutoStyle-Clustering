import collections

def word_count(phrase):
    counts = collections.defaultdict(int)
    for word in phrase.split():
        counts[word] += 1
    return counts
