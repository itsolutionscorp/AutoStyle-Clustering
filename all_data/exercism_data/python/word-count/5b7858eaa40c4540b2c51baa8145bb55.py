import re
from collections import defaultdict

def word_count(sentence):
    counts = defaultdict(int)
    words = re.findall('\w+', sentence)
    
    for word in words:
        word = word.lower()
        counts[word] += 1

    return counts
