from collections import Counter
def word_count(words):
    words = words.replace("\n", " ").replace("\t", " ")
    while "  " in words:
        words = words.replace("  ", " ")
    wordsSplit = words.split()
    wordCounts = Counter(wordsSplit)
    return wordCounts
