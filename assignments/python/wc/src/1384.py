import re
from collections import Counter
def word_count(phrase):
    words = re.findall(r'\w+', phrase.lower())
    return Counter(words)
