from collections import Counter
from string import punctuation
def word_count(sentence):
    sentence = ''.join(char.lower() for char in sentence if char not in punctuation)
    return Counter(sentence.split())
