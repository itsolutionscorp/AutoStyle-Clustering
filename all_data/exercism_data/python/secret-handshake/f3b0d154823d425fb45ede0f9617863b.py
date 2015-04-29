signs = {1: 'wink', 2: 'double blink', 4: 'close your eyes', 8 : 'jump'}
codes = {'wink': 1, 'double blink': 2, 'close your eyes': 4, 'jump': 8}

def handshake(cd):
    try:
        c = int(cd, 2)
    except(ValueError):
        return []
    except(TypeError):
        c = cd

    if not 0 < c < 32:
        return []

    result = []
    for i in sorted(signs.keys()):
        if c & i > 0:
            result.append(signs[i])
    if c & 16:
        result = list(reversed(result))
    return result

def code(h):
    rev = False
    prevCode = 0
    result = 0
    for s in h:
        try:
            result += codes[s]
            if codes[s] < prevCode:
                rev = True
            prevCode = codes[s]
        except (KeyError):
            return '0'
    if rev:
        result += 16
    return "{0:b}".format(result)
