action = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):
    ret = []
    bits = 0
    rev = False

    if type(code) == type(1):
        bits = code
    if type(code) == type(''):
        try:
            bits = int(code, 2)
        except ValueError:
            pass

    if bits < 1:
        return ret

    if bits & 16:
        rev = True
        bits &= 15

    i = 0
    while bits > 0:
        if bits & 1:
            if rev:
                ret.insert(0, action[i])
            else:
                ret.append(action[i])
        bits /= 2
        i += 1

    return ret

def code(arr):
    ret = []

    r = match_list(arr)
    if len(r) == 0:
        r = match_list(arr[::-1])
        if len(r) > 0:
            r += [4]
        
    return bin(sum(2**x for x in r))[2:]

def match_list(a):
    ret = []
    i = 0

    for j in range(len(action)):
        if action[j] == a[i]:
            ret.append(j)
            i += 1
            if i >= len(a):
                break
    else:
        ret = []

    return ret
