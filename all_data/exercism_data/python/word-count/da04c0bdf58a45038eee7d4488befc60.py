from collections import Counter

def word_count(words):
    if not isinstance(words,basestring):
        return None
    word_counts = Counter()
    for word in words.split():
        word_counts[word] += 1
    return word_counts
