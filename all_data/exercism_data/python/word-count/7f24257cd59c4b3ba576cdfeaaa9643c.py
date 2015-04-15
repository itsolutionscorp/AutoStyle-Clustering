from collections import Counter
import string

def word_count(txt):
    return Counter([word.lower() for word in txt.translate(None, string.punctuation).split()])
