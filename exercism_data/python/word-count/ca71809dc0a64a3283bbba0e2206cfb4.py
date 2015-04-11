import re
from collections import Counter

def word_count(sentence):
    return Counter(re.findall(r'[0-9a-z]+', sentence.lower()))
