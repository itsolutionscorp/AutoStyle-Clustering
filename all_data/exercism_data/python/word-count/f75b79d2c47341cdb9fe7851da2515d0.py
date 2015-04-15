def word_count(words):
    d = {}
    for w in words.split():
        low = ''.join(c for c in w.lower() if c.isalnum())
        if low!='':
            d[low] = d.get(low, 0) + 1
    return d
