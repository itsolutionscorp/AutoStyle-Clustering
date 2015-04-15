from itertools import compress
from functools import reduce


signals = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(n):
    if type(n) is int:
        n = bin(n)[2:]

    if any(c != '0' and c != '1' for c in n):
        return []

    sn = list(map(lambda c: c == '1', n.zfill(5)[::-1]))
    res = list(compress(signals, sn))
    if sn[4]:
        res.reverse()
    return res

def code(shakes):
    try:
        n = reduce(lambda total, hs: total + 2 ** signals.index(hs), shakes, 0)
    except:
        return '0'
    if len(shakes) > 1 and signals.index(shakes[0]) > signals.index(shakes[1]):
        n += 16
    return bin(n)[2:]
