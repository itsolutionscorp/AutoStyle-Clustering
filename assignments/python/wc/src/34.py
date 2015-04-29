import collections
def word_count(text):
    words = text.split()
    return collections.Counter(words)
