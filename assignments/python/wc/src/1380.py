import re
from collections import Counter
def word_count(words):
    return dict(Counter(map(str.lower, re.findall('\w+', words))))
