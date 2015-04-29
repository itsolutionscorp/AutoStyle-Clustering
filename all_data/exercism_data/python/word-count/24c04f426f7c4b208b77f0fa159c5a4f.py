from collections import Counter
import re

def word_count(sentence):
    word_counter = Counter()
    sentence = re.split('\W+', sentence)
    for word in sentence:
        if word:
            word_counter[word.lower()]+=1
    return word_counter
