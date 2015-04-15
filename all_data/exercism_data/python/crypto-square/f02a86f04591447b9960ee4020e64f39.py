from __future__ import division

import math

def clean(msg):
    return [c.lower() for c in msg if c.isalnum()]

def horizontal(square_size):
    for y in range(square_size):
        for x in range(square_size):
            yield x, y

def vertical(square_size):
    for y, x in horizontal(square_size):
        yield x, y


def encode(msg):
    msg = clean(msg)

    if not msg:
        return ""

    square_size = int(math.ceil(math.sqrt(len(msg))))
    square = [[''] * square_size for _ in range(square_size)]

    for (x, y), c in zip(horizontal(square_size), msg):
        square[x][y] = c

    cipher = "".join((square[x][y] for x, y in vertical(square_size)))

    return " ".join((cipher[start : start + 5] for start in range(0, len(cipher), 5)))

def decode(msg):
    msg = clean(msg)

    if not msg:
        return ""

    square_size = int(math.ceil(math.sqrt(len(msg))))
    square = [[''] * square_size for _ in range(square_size)]

    read_order = list(vertical(square_size))[:len(msg)]
    write_order = sorted(read_order, key = lambda x: x[::-1])

    for (x, y), c in zip(write_order, msg):
        square[x][y] = c

    return "".join((square[x][y] for x, y in read_order))
