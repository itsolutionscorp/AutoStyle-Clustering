import re
from collections import defaultdict

spaces = re.compile("[ ]+")

def tokenize(phrase):
    lines = phrase.split("\n")
    return [word
            for line in lines
            for word in re.split(spaces, line)]

def count(words):
    counts = defaultdict(lambda: 0)
    for word in words:
        counts[word] += 1
    return dict(counts)

def word_count(phrase):
    words = tokenize(phrase)
    counts = count(words)
    return counts
