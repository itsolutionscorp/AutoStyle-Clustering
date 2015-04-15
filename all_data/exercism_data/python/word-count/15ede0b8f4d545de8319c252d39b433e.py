def word_count(str):
    dict = {}
    l = str.split()
    for s in l:
        dict[s] = l.count(s)

    return dict
