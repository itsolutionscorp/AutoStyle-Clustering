from collections import defaultdict

def word_count(text):
    words = [word for word in text.split() if word]
    counts = defaultdict(int)
    for word in words:
        counts[word] += 1
    return counts
