import string, collections, itertools
def word_count(arg):
    allowed = set(itertools.chain(string.ascii_lowercase, string.digits))
    words = ''.join(c if c in allowed else ' ' for c in arg.lower()).split()
    return dict(collections.Counter(words))
