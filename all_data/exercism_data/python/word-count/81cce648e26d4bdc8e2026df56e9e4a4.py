import re
from collections import defaultdict

def word_count(string):
    freq = defaultdict(int)
    words = re.sub(' +', ' ', string).split()
    for word in words:
        freq[word] = freq.get(word, 0) + 1
    return freq
