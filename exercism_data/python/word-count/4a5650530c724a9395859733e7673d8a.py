from collections import defaultdict

def word_count(sentence):
    words = sentence.split()
    # defaultdict(int) sets the default when no key is found to 0, and then
    # I can increment with the += operator.
    count = defaultdict(int)
    for w in words:
        count[w] += 1
    return count
