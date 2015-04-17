from collections import Counter
def word_count(text):
    u"""Return the number of words in the given string."""
    return Counter([word for word in text.split()])
