import collections
def word_count(word):
    word = word.split()
    return dict(collections.Counter(word))
