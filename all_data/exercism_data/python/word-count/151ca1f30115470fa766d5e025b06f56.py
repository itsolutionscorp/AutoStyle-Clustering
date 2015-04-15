from collections import Counter

def word_count(phrase):
    counter = Counter()
    for word in phrase.strip().split():
        counter[word] += 1
    return counter
