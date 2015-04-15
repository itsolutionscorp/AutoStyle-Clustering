from string import ascii_lowercase
from string import digits
from math import ceil

def encode(message):
    message = ''.join([c for c in message.lower() if c in ascii_lowercase + digits])
    L = len(message)
    dim = int(ceil(L**0.5))
    square = ''
    for i in range(dim):
        for j in range(i, L, dim):
            square += message[j]
        square += ' '
    return square[:-1]
