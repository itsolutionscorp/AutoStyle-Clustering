import re
from collections import Counter

def word_count(phrase):
    return Counter(re.sub(r'[^a-z0-9\[\]]',' ', phrase.lower()).split())  # is this too confusing?
