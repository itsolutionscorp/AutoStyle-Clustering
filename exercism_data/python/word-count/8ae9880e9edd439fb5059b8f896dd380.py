from collections import defaultdict


def word_count(sentence):
    freq_map = defaultdict(int)
    words = sentence.split()

    for word in words:
        freq_map[word]+=1

    return freq_map
