from collections import defaultdict

def word_count(string):
    frequencies = defaultdict(int)

    for word in string.split():
        frequencies[word] += 1

    return frequencies
