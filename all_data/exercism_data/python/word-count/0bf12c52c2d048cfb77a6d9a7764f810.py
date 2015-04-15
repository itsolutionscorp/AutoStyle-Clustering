from collections import defaultdict

def word_count(phrase):
    counter = defaultdict(int)
    for word in phrase.split():
        counter[word] += 1
    return counter
