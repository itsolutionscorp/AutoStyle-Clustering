def word_count(s):
    wl = s.split()
    return {w:wl.count(w) for w in set(wl)}
