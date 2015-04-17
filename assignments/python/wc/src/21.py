import re
from collections import Counter
def word_count(phrase):
    normalized = re.sub('[^\s0-9a-z]', '', phrase.lower())
    word_counts = Counter(normalized.split())
    return word_counts
