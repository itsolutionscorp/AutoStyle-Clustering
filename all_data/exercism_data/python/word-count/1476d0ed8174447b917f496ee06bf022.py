from collections import Counter


def word_count(input_words):
    words = input_words.split()
    counter = Counter()
    for word in words:
        counter[word] += 1
    print counter
    return counter
