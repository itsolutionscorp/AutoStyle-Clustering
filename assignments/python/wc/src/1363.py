import string, re
def word_count(arg):
    arg = arg.lower()
    pattern = '[' + string.punctuation + '\s]'
    words = [x for x in re.split(pattern, arg) if x]
    rv = {}
    for s in words:
        rv[s] = rv.get(s, 0) + 1
    return rv
