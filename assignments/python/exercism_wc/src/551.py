import re
from collections import Counter
def word_count(text):
    words = re.split(r"\s+", text)
    wc = Counter(words)
    return wc
