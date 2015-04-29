from collections import Counter

def word_count(sentence):
    words = sentence.split()
    # The Counter class creates a dictionary with a default value of zero for
    # everything, then I can increment with the += operator.
    count = Counter()
    for w in words:
        count[w] += 1
    return count
