from collections import defaultdict
import re

def word_count(s):
    counts = defaultdict(int)
    for w in re.findall(r'\w+', s):
        counts[w.lower()] += 1
    return dict(counts)
