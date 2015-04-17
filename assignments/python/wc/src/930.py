import re
from collections import Counter
def word_count(text):
    return Counter(re.findall(r'\w+', text.lower()))
