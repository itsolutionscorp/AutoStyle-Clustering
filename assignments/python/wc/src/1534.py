from collections import Counter
def word_count(text):
    """
    Returns a dictionary recording the occurance of each unique word in 'text'.
    For each key-value pair, the key is the word in question, and the value is
    the number of occurances of that word in 'text'.
    """
    return Counter(text.split())
