from collections import defaultdict

def word_count(phrase):
    words = defaultdict(int)
    for word in phrase.split():
        words[word] += 1
    return words
