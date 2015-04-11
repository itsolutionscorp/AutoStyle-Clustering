from itertools import compress


signals = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(n):
    sn = map(lambda c: c == '1', bin(n).zfill(5)[::-1])
    return list(compress(signals, sn))

def code():
    return 0
