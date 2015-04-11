digits = {' _ | ||_|   ': '0',
          '     |  |   ': '1'}


def grid(s):
    for k, v in digits.items():
        if v == s:
            return [k[0:3], k[3:6], k[6:9], k[9:12]]
    raise ValueError


def number(g):
    k = ''.join(g)
    if len(k) != 12:
        raise ValueError
    if k in digits.keys():
        return digits[''.join(g)]
    else:
        return '?'
