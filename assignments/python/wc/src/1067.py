def word_count(text):
    """Returns a dictionary containing counts of each word in text"""
    words = text.strip().split()
    counts = dict.fromkeys(words, 0)
    for word in words:
        counts[word] += 1
    return counts
