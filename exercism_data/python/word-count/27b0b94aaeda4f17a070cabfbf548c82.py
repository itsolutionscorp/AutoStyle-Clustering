from collections import defaultdict
def word_count(phrase):
    counts = defaultdict(int)
    for w in phrase.split(): counts[w] += 1	
    return dict(counts)
