import string, re

def word_count(arg):
    arg = arg.lower()
    # generate pattern that matches any punctuation or whitespace character
    pattern = '[' + string.punctuation + '\s]'
    # split into non-empty words as separated by pattern
    words = [x for x in re.split(pattern, arg) if x]

    rv = {}
    for s in words:
        # initialize with 1 if s is not in rv; otherwise, increment rv[s]
        rv[s] = rv.get(s, 0) + 1
    return rv
