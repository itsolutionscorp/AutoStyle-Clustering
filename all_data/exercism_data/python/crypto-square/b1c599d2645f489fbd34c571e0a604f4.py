from math import sqrt
from math import ceil


def encode(words=''):
    ret, par, words = [], '', words.lower()

    if len(words) > 1:
        for i in words:
            if i in 'abcdefghijklmnopqrstuvwxyz0123456789':
                par += i

        a = len(par)
        b = round(sqrt(a))
        c = ceil(a / b)
        d = [list(par[i:i+c]) for i in range(0, a, c)]
        for i in range(len(d[0])):
            tmp = ''
            for v in d:
                if v:
                    tmp += v.pop(0)
            ret.append(tmp)

    return ' '.join(ret)


def decode(words=''):
    out = ''
    x = [list(x) for x in words.split()]
    for i in range(len(x[0])):
        for v in x:
            if v:
                out += v.pop(0)
    return out
