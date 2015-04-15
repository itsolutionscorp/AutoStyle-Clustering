from collections import Counter

def word_count(words):
    while "  " in words:
        words = words.replace("  ", " ")
    wordsSplit = words.replace("\n", " ").replace("\t", " ").split()
    wordCounts = Counter(wordsSplit)
    return wordCounts
