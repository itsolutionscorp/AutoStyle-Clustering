# Word Count
from collections import Counter


def word_count(words):
    
    # splits words into list
    split_words = words.split()
    
    # counter returns count of each list item in a dict
    return Counter(split_words)
    
