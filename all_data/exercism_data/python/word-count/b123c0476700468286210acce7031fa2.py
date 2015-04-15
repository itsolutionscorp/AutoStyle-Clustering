from collections import defaultdict
import re


def word_count(phrase):
    counts = defaultdict(int)
    for word in re.sub(r'\W+', ' ', phrase).lower().split():
        counts[word] += 1
    return dict(counts)
