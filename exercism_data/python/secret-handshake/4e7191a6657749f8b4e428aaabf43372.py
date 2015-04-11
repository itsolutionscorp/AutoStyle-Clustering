__author__ = 'emiller42'

decode_steps = {1: 'wink',
                2: 'double blink',
                4: 'close your eyes',
                8: 'jump'}

encode_steps = {'wink': 1,
                'double blink': 2,
                'close your eyes': 4,
                'jump': 8}


def handshake(encoded):
    output = []

    if isinstance(encoded, basestring):
        try:
            encoded = int(encoded, 2)
        except ValueError:
            return output

    if 0 < encoded < 32:
        for step in sorted(decode_steps.keys(), reverse=encoded&16):
            if encoded & step:
                output += [decode_steps[step]]
    return output


def code(steps):
    try:
        encoded = [encode_steps[step] for step in steps]
    except KeyError:
        return '0'
    if encoded == sorted(encoded):
        return bin(sum(encoded))[2:]
    else:
        return bin(sum(encoded) + 16)[2:]
