from collections import Counter
def word_count(text):
    counts = Counter()  # type: Dict[str, int]
    for word in text.split():
        word = word.strip()
        counts[word] += 1
    return counts
