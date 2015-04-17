def word_count(phrase):
    p = phrase.strip('\n')
    s = p.split()
    d = {}
    for i in s:
        if i in d:
            d[i] += 1
        else:
            d[i] = 1
    return d
