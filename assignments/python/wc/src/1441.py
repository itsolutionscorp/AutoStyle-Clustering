from collections import Counter
def word_count(words):
    split_words = words.split()
    return Counter(split_words)
