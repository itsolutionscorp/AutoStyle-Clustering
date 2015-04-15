import re
from collections import Counter

def word_count(sentence):
    words = Counter()
    sentence = re.findall(r'\w+', sentence.lower())
    for word in sentence:
        if word:
            words[word] += 1
    
    return words
