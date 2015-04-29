from collections import Counter
def word_count(str):
    ignore = ",:!&@$%^&'"
    for c in ignore:
        str = str.replace(c, '')
    return Counter([s.lower() for s in str.split()])
