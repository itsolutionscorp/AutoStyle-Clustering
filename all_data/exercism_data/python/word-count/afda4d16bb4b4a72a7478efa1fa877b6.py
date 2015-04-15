from collections import Counter

def word_count(phrase):
    words = Counter()
    for word in phrase.split():
        words[word] += 1
    return words
