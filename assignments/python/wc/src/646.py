from collections import Counter
def word_count(phrase):
    word_counter = Counter()
    for word in phrase.split():
        word_counter[word] += 1
    return word_counter
