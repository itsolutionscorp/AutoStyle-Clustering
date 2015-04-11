from collections import Counter

def word_count(words):
    textArray = words.split()
    return Counter(textArray)
