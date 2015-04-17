from collections import Counter
def word_count(sentence):
    counter = Counter(sentence.split())
    return counter
