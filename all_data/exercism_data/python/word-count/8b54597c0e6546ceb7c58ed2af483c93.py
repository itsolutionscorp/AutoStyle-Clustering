import re
from collections import defaultdict

def word_count(sentence):
    counts = defaultdict(int)
    words = re.split('\W+', sentence)
    
    for word in words:
        if not word == '':
            word = word.lower()
            counts[word] += 1

    return counts
