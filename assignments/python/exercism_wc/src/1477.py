from collections import Counter
def word_count(sentence):
    sentence = sentence.split()
    return dict(Counter(sentence))
