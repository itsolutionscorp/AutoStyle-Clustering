from collections import Counter
import re
def word_count(word):
    return dict(Counter(re.findall('[^\s]+', word)))
