from collections import Counter

def word_count(text):

    word_dict = Counter(text.strip().split())

    return word_dict
