from collections import Counter
def word_count(s):
    """
    Return a dict with 'word': number of occurrences for each word in `s`.
    """
    counts = Counter()
    for word in s.split():
        counts[word] += 1
    return dict(counts)
