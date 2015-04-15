from collections import defaultdict

def word_count(text):
    c = defaultdict(int)
    for w in text.split():
        c[w] += 1
    return c
