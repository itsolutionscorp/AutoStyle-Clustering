from collections import Counter
def word_count(text):
    ctr = Counter(text.split())
    return ctr
