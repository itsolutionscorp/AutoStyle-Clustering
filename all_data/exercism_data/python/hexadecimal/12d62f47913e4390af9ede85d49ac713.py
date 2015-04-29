dc = {
    'a' : 10,
    'b' : 11,
    'c' : 12,
    'd' : 13,
    'e' : 14,
    'f' : 15
    }


def hexa(string):
    return sum([dc[i]*16**cnt if i in dc.keys() else int(i)*16**cnt for cnt,i in enumerate(reversed(string.lower()))])
