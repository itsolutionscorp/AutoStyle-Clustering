from collections import Counter
def word_count(text):
    """
    Return a dict of all word in given text and their number of occurrence.
    """
    return Counter(text.split())
