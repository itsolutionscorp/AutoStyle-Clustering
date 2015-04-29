from collections import Counter
def word_count(words):
    wordsSplit = words.split()
    return Counter(wordsSplit)
