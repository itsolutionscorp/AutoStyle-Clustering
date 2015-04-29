from collections import Counter
import string

def word_count(words):
    return Counter(words.lower().translate(None, string.punctuation).split())
        
