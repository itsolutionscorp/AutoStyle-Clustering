from collections import defaultdict


def word_count(phrase):
    word_count = defaultdict(int)
    for word in phrase.split():
        word = ''.join([c for c in word.lower() if c.isalnum()])
        if word:
            word_count[word] += 1
    return word_count
