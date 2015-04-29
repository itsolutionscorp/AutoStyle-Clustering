from collections import defaultdict

def word_count(text):
    count = defaultdict(int)
    for t in text.split():
        count[t] += 1
    return count
