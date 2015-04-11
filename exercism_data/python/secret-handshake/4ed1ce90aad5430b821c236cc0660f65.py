actions = {1:'wink', 2:'double blink', 4:'close your eyes', 8:'jump'}
codes = {v:k for k,v in actions.items()}
A = len(actions)
REV = 2 ** A
MAX = 2 ** (A+1)

def handshake(n):
    try:
        n = int(n, 2)
    except ValueError:
        return []
    except TypeError:
        pass
    if not 0 < n < MAX:
        return []
    shake = [actions[a] for a in sorted(actions) if a & n]
    if REV & n:
        shake.reverse()
    return shake
    
def code(shake):
    try:
        num = sum([codes[s] for s in shake])
        if len(shake) > 1 and codes[shake[0]] > codes[shake[1]]:
            num += REV
    except KeyError:
        num = 0
    return "{0:b}".format(num)
