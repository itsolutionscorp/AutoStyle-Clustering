from collections import Counter
def word_count(string):
    list = string.split()
    return Counter(list)
