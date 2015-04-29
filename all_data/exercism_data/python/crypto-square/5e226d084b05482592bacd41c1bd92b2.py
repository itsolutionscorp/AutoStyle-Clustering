
from math import ceil
from itertools import zip_longest

def normalize(text):
    return ''.join(char
                   for char in text.lower()
                   if char.isalnum())

def partition(list, size):
    return [list[start:start+size]
            for start in range(0, len(list), size or 1)]

def transpose_and_join(matrix):
    # can't just use zip(*matrix) because the last row may be incomplete
    return ''.join(normalize(''.join(row))
                   for row in zip_longest(*matrix, fillvalue=' '))

def encode(cleartext):
    norm_text = normalize(cleartext)

    square = partition(norm_text, ceil(len(norm_text)**0.5))

    trans_square = transpose_and_join(square)

    cyphertext = ' '.join(partition(trans_square, 5))

    return cyphertext

def decode(cyphertext):
    norm_cyph = normalize(cyphertext)
    num_rows = ceil(len(norm_cyph)**0.5)
    num_cols = ceil(len(norm_cyph)/num_rows)
    num_extra = len(norm_cyph)%num_rows

    # for the first num_extra rows, partition by num_cols
    # after that partition by num_cols-1
    split_at = num_cols*num_extra
    extra_rows, regular_rows = norm_cyph[:split_at], norm_cyph[split_at:]
    square = partition(extra_rows, num_cols) + partition(regular_rows, num_cols-1)

    cleartext = transpose_and_join(square)
    return cleartext
