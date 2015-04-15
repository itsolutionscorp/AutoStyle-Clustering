from collections import defaultdict
from string import punctuation

def word_count(sentence):
    counts = defaultdict(int)
    sentence = filter(lambda c: c not in punctuation, sentence).lower()
    words = sentence.split()
    for word in words:
        counts[word] += 1
    return counts
