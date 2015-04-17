from string import punctuation
from collections import Counter
def word_count(some_string):
    return Counter(some_string.translate(None, punctuation).lower().split())
