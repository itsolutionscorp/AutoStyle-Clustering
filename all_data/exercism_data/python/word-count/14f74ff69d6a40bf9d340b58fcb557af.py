# script for Exercism project wordcount

from collections import Counter

def word_count(str):
    return Counter(str.split())
