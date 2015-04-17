from collections import Counter
import re
def word_count(s):
    strip = lambda w: re.sub(r'[\W]', '', w).lower()
    return Counter((strip(w) for w in s.split() if strip(w)))
