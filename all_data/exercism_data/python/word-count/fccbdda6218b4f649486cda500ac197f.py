import string, collections
def word_count(words):
    return collections.Counter(words.translate(None, string.punctuation).lower().split())
