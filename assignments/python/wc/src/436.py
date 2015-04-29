import re
from collections import Counter
def word_count(sentence):
    return Counter(re.split('\s+', sentence))
