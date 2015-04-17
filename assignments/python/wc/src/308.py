def word_count(strings):
    d = {}
    for s in strings.split():
        if d.has_key(s):
            d[s] = d[s] + 1
        else:
            d[s] = 1
    return d
