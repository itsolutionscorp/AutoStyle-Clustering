import string
from collections import Counter
def word_count(text):
    return Counter(text.lower().translate(None, string.punctuation).split())
