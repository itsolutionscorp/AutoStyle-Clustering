import string
from collections import Counter

def strip_punctuation(word):
    exclude = set(string.punctuation)
    word = ''.join(ch for ch in word if ch not in exclude)
    return word

def word_count(text):
    words = text.split(" ")

    count = Counter()
    for word in words:
        word = strip_punctuation(word.lower())
        if word:
            count[word] += 1

    return count
