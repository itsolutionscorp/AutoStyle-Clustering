from collections import Counter
from string import ascii_lowercase, digits
_whitelist = set(ascii_lowercase).union(set(digits))
def word_count(arg):
    words = ''.join(c if c in _whitelist else ' ' for c in arg.lower())
    return dict(Counter(words.split()))
