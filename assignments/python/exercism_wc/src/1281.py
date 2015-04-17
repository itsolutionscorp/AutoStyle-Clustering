from collections import Counter
def word_count(a_string):
    words = a_string.split()
    word_count = Counter()
    for word in words:
        word_count[word] += 1
    return word_count
