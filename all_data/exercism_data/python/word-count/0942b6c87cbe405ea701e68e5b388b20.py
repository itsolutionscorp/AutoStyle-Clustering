from collections import Counter

def word_count(what):
    count = Counter()
    for word in what.split():
        count[word] += 1
    return count
