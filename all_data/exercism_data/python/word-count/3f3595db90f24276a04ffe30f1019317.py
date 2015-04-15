import re
from collections import Counter

def word_count(string):
    return Counter(filter(None, re.split('\W+', string.lower())))
