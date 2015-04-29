from collections import Counter
import re
def word_count(string):
    return Counter(re.sub(r'\W+', ' ', string.lower()).split())
