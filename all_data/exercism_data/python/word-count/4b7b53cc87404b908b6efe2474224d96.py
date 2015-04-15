from collections import defaultdict


def word_count(sentence):
    n_words = defaultdict(int)
    sentence = sentence.split()
    for word in sentence:
        n_words[word] += 1
    return n_words
