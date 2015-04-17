import collections
def word_count(sentence):
    c = collections.Counter(sentence.split())
    return dict(c)
