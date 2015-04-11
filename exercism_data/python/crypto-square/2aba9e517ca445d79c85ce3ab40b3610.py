from __future__ import division

import math


def encode(plain_text):
    plain_text = clean(plain_text)

    square = get_empty_square(plain_text)
    write_order = horizontal(len(square))
    read_order = vertical(len(square))
    cipher = write_and_read(square, plain_text, write_order, read_order)

    return " ".join(cipher[start : start + 5]
                    for start in range(0, len(cipher), 5))

def decode(cipher):
    cipher = cipher.replace(" ", "")

    square = get_empty_square(cipher)
    #write the msg in the same order it was read.
    order_written = list(horizontal(len(square)))[:len(cipher)]
    #make the order vertical
    write_order = sorted(order_written, key= lambda x: x[::-1])
    #read it in the same order it was written
    read_order = horizontal(len(square))
    plain_text = write_and_read(square, cipher, write_order, read_order)

    return plain_text

def clean(msg):
    """Convert msg to lower, remove non-alphanumeric characters"""
    return [c.lower() for c in msg if c.isalnum()]

def get_empty_square(msg):
    """Create an empty square big enough to fit the msg in"""
    square_size = int(math.ceil(math.sqrt(len(msg))))
    square = [[''] * square_size for _ in range(square_size)]

    return square

def horizontal(square_size):
    """Yield the positions within the square in horizontal order"""
    for x in range(square_size):
        for y in range(square_size):
            yield x, y

def vertical(square_size):
    """Yield the positions within the square in vertical order"""
    for y, x in horizontal(square_size):
        yield x, y

def write_and_read(square, msg, write_order, read_order):
    """Write a message in write_order into the square, read it in read_order"""

    for (x, y), c in zip(write_order, msg):
        square[x][y] = c

    return "".join(square[x][y] for x, y in read_order)
