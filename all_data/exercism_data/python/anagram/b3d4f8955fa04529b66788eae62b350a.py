import collections

compare = lambda x, y: collections.Counter(x) == collections.Counter(y)

def detect_anagrams(n, lists):
    returned = []
    charray = list(n.upper())
    for k in lists:
        tempk = list(k.upper())
        if(compare(charray, tempk) and k.upper() != n.upper()):
            returned.append(k)
    return returned
