from collections import Counter

def word_count(testStr):

    # Counts every word and returns a dictionary with results
    return Counter(testStr.split())
