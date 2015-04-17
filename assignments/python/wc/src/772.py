from collections import Counter
def word_count(text):
    counts = Counter()
    for word in text.split():
        word = word.strip()
        counts[word] += 1
    return counts
