import re
from collections import Counter
def word_count(phrase):
    words = re.sub(r'[^a-z0-9\[\]]',' ', phrase.lower()).split()  # is this line too confusing?
    word_count = Counter()
    for word in words:
        word_count[word] += 1
    return word_count
