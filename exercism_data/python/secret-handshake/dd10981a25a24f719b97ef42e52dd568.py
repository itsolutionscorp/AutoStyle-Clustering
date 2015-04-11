from itertools import compress


signals = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(n):
    if type(n) is int:
        n = bin(n)
    sn = list(map(lambda c: c == '1', n.zfill(5)[::-1]))
    res = list(compress(signals, sn))
    if sn[4]:
        res.reverse()
    return res

def code():
    return 0
