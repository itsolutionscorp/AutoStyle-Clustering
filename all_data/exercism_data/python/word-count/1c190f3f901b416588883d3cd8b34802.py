from collections import defaultdict 

def word_count(s):
    counts = defaultdict(lambda: 0)
    for word in s.split():
        counts[word] += 1

    return counts
