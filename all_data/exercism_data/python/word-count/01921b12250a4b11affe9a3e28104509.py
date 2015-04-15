
##from collections import Counter

def word_count(s):
##    return Counter(s.split())
    counts = {}
    for word in s.split():
        counts[word] = counts.get(word, 0) + 1
    return counts
