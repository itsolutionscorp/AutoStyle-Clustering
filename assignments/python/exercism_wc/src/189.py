from collections import Counter
def word_count(words):
    words_split = words.split()
    return Counter(words_split)
