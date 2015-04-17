import re
from collections import Counter
def word_count(phrase):
    return Counter(re.findall(r'\w+', phrase.lower()))
