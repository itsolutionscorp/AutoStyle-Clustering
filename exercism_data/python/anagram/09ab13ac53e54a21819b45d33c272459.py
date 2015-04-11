def detect_anagrams(src, words):
    ret = []
    t1 = sort_string(src)

    for wd in words:
        t2 = sort_string(wd)

        if t1 == t2 and src.lower() != wd.lower():
            ret.append(wd)

    return ret

def sort_string(st):
    ls = []
    ret = ''
    for c in range(len(st)):
        ls.append((st[c]).lower())
    ls.sort()

    for c in ls:
        ret += c

    return ret
