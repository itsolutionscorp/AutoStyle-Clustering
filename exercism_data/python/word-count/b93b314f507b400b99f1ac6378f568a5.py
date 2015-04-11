from collections import Counter
import string

exclude = set(string.punctuation)
table = string.maketrans("","")

def word_count(words):
    words = words.translate(table, string.punctuation)
    words = words.lower()
    wordcount = words.split()
    for word in wordcount:
        return Counter(wordcount)
