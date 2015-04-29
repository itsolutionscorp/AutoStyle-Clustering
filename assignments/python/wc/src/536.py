from collections import Counter
def word_count(text):
    textArray = text.split()
    return Counter(textArray)
