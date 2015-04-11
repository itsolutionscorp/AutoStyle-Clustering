from string import ascii_lowercase as lowers, maketrans, translate

table = maketrans(lowers, lowers[::-1])


def encode(s):
    encoded = filter(lambda l: l.isalnum(), translate(s.lower(), table))
    return ' '.join([encoded[i:i + 5] for i in range(0, len(encoded), 5)])


def decode(s):
    return translate(s.replace(' ', ''), table)
