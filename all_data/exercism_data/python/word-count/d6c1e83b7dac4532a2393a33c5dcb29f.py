from collections import Counter

def word_count (statement):
    words = statement.split()
    return Counter(words)
