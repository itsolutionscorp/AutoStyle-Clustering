import math
from itertools import chain

def row_len(message):
    return int(math.ceil(math.sqrt(len(message))))

def encode(message):
    string = ''.join(ch for ch in message.lower() if ch.isalnum())
    if len(string) == 0:
        return ''

    row_length = row_len(string)
    indices = range(0, len(string), row_length)
    matrix = []
    for x in indices:
        matrix.append(string[x:x + row_length])
    code = []
    for i in range(row_length):
        for row in matrix:
            if i < len(row):
                code.append(row[i])
        code.append(' ')
    return ''.join(code).rstrip()

#do it all in one loop. ok.
