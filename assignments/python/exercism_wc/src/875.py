from collections import Counter
import string
def word_count(text):
    table = string.maketrans("", "")
    text = text.lower().translate(table, string.punctuation)
    return Counter(text.split())
