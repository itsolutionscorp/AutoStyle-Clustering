__author__ = 'Hinek'

def distance(str1, str2):
    dif = 0
    for a, b in zip(str1, str2):
        if a != b:
            dif += 1
    return dif
