from collections import Counter
from string import punctuation
def word_count(sentence):
    sentence = filter(lambda c: c not in punctuation, sentence).lower()
    words = sentence.split()
    return Counter(words)
