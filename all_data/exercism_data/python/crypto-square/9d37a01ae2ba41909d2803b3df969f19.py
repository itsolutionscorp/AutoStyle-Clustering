from math import ceil

def encode(message):
    message = ''.join(c.lower() for c in message if c.isalnum())
    n = ceil(len(message) ** .5)
    return ' '.join(message[i::n] for i in range(n))
