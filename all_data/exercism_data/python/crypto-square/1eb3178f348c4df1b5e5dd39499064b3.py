from math import sqrt, ceil

def encode(message):
    out = ''
    message, n = _normalize(message)
    for i in range(n):
        for part in message:
            if part: out += part.pop(0)

        out += ' '
    return out.strip()


def _normalize(message):
    message = ''.join([c.lower() for c in message if c.isalnum()])
    n = ceil(sqrt(len(message))) or 1

    return [list(message[i:i+n])
            for i in range(0, len(message), n)] , n
