def score(text):
    map = {'AEIOULNRST': 1, 'DG': 2, 'BCMP': 3, 'FHVWY': 4, 'K': 5, 'JX': 8, 'QZ': 10 }
    ret = 0
    for x in text.upper():
        for k, v in map.items():
            if set(k) & set(x) == set(x):
                ret += v
    return ret
