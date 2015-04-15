from collections import defaultdict

def word_count(words):
    count = defaultdict(int)

    for w in words.split():
        count[w] += 1

    return count
