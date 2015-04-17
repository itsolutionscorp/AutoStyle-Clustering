from string import punctuation
from collections import Counter
def word_count(some_string):
    some_string = some_string.translate(None, punctuation).lower().split()
    answers = Counter(some_string)
    return answers
