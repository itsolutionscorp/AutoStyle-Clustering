from collections import Counter
def word_count(phrase):
    l = str.split(phrase)
    count = Counter(l)
    return count
