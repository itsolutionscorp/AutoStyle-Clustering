from collections import Counter

def word_count(message):
    words = Counter()
    words.update(message.split())
    return words
