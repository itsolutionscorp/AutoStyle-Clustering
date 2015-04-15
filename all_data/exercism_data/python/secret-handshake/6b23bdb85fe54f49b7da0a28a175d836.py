from itertools import compress


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

def code():
    return 0
