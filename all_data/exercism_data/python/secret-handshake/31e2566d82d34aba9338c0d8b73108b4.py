codes = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):
    try:
        if type(code) == str: code = int(code, 2)
        if code > 31 or code < 0: raise ValueError
    except ValueError: return []
    ret = [codes[i] for i in range(4) if (code & 2**i) >= 1 ]
    if (code & 0x10) == 0x10: ret.reverse()
    return ret

def code(shake):
    coded = forward_shake(shake)
    if coded != None: return coded
    coded = backward_shake(shake)
    if coded != None: return '1' + coded
    return '0'

def forward_shake(shake):
    testshake, ret = shake[:], ''
    for c in codes:
        if testshake == []: break
        if c == testshake[0]:
            ret = '1' + ret
            testshake = testshake[1:]
        else:
            ret = '0' + ret
    if len(testshake) == 0: return ret
    return None

def backward_shake(shake):
    testshake, ret = shake[:], ''
    for c in codes[::-1]:
        if len(testshake) > 0 and c == testshake[0]:
            ret = ret + '1'
            testshake = testshake[1:]
        else:
            ret = ret + '0'
    if len(testshake) == 0: return ret
    return None
