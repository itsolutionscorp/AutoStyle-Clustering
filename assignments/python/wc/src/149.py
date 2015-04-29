from collections import Counter
import re
def word_count(phrase):
    words = re.split('\s', phrase)
    count = Counter()
    for word in words:
        if not word:
            continue
        count[word] += 1
    return dict(count)
