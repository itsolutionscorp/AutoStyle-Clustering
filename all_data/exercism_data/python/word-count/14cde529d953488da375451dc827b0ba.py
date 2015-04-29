def word_count(s):
    """
    Return a dict with 'word': number of occurrences for each word in `s`.
    """
    counts = {}
    for word in s.split():
        counts[word] = counts.get(word, 0) + 1
    return counts
