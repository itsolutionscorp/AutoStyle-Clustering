from collections import defaultdict
def word_count(phrase):
    counts = defaultdict(int)
    words = [w.strip('\n') for w in phrase.split() if w ]
    for w in words: counts[w] += 1	
    return dict(counts)
