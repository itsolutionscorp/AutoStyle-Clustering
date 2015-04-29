def word_count(s):
    r={}
    n=''.join([l for l in s.lower() if l in 'abcdefghijklmnopqrstuvwxyz123456789 ']).split(' ')
    for w in set(n):
        if w:r[w]=n.count(w)
    return r
