from collections import Counter
def word_count(text):
    ctr = Counter()
    for word in text.split():
        ctr[word] += 1
    return ctr
