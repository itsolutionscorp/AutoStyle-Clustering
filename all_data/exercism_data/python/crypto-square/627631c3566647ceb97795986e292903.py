
from math import ceil
from itertools import zip_longest

def normalize(text):
    return ''.join(char.lower()
                   for char in text
                   if char.isalnum())

def partition(list, size):
    return [list[start:start+size]
            for start in range(0, len(list), size or 1)]

def transpose_and_join(matrix):
    # can't just use zip(*matrix) because the last row may be incomplete
    return ''.join(normalize(row)
                   for row in zip_longest(*matrix, fillvalue=' '))

def encode(cleartext):
    norm_text = normalize(cleartext)
    square = partition(norm_text, ceil(len(norm_text)**0.5))
    return ' '.join(partition(transpose_and_join(square), 5))

def decode(cyphertext):
    norm_cyph = normalize(cyphertext)
    cyph_len = len(norm_cyph)
    num_rows = ceil(cyph_len**0.5)
    num_cols = ceil(cyph_len/num_rows)
    num_extra = cyph_len%num_rows

    # for the first num_extra rows, partition by num_cols
    # after that partition by num_cols-1
    split_at = num_cols*num_extra
    extra_rows, regular_rows = norm_cyph[:split_at], norm_cyph[split_at:]
    square = partition(extra_rows, num_cols) + partition(regular_rows, num_cols-1)
    return transpose_and_join(square)
