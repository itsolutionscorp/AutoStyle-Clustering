import string, collections, itertools

def word_count(arg):
    # set of allowed chars: all lowercase roman characters & digits
    allowed = set(itertools.chain(string.ascii_lowercase, string.digits))

    # make a string of all allowed characters lowercased & split on whitespace
    words = ''.join(c if c in allowed else ' ' for c in arg.lower()).split()

    # use Counter to quickly count occurrence of each word & return as dict
    return dict(collections.Counter(words))
