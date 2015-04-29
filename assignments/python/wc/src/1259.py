'''word count exercise'''
import string
from collections import Counter
def word_count(buf):
    '''return a dictionary of words and the count of their occurrences'''
    buf = buf.lower()               # normalize to lower case
    buf = ''.join(c for c in buf if c not in string.punctuation)
    words = buf.split()             # split them out on spaces
    return dict(Counter(words))     # count and return dict object
