from collections import Counter
def word_count(text):
    counts = Counter()
    stripped_text = ''.join(c for c in text.lower()
                            if c.isalnum() or c.isspace())
    for w in stripped_text.split():
        counts[w] += 1
    return counts
