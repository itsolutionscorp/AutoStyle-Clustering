import collections
import string
def word_count(phrase):
    replace_punctuation = string.maketrans(string.punctuation, ' '*len(string.punctuation))
    sanitized_phrase = phrase.translate(replace_punctuation)
    tokens = sanitized_phrase.lower().split()
    occurrences = collections.Counter(tokens)
    return dict(occurrences)
