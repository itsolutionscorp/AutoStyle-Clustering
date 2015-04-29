_actions = ('wink', 'double blink', 'close your eyes', 'jump')


def handshake(c):
    if isinstance(c, str):
        try:
            c = int(c, base=2)
        except ValueError:
            return []
    if c < 0:
        return []
    acts = [action for n, action in enumerate(_actions) if c & 2 ** n]
    if c & 16:
        acts.reverse()
    return acts


def code(c):
    if isinstance(c, int):
        return bin(c)[2:]
    elif isinstance(c, list):
        key = {k: 2 ** n for n, k in enumerate(_actions)}
        try:
            acts = [key[act] for act in c]
        except KeyError:
            return '0'
        n = reduce(lambda x, y: x | y, [key[act] for act in c], 0)
        if acts != sorted(acts):
            n |= 16
        return bin(n)[2:]
    return c
