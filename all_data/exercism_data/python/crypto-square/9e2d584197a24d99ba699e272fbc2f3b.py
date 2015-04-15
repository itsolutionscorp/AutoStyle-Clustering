
from math import ceil
from itertools import zip_longest

def normalize(text):
    return ''.join(char
                   for char in text.lower()
                   if char.isalnum())

def partition(list, size):
    return [list[start:start+size]
            for start in range(0, len(list), size)]

def row_size(text):
    return ceil(len(text)**0.5)

def split_square(text):
    return partition(text, row_size(text))

def transpose(matrix):
    # can't just use zip(*matrix) because the last row may be incomplete
    return [''.join(row[col_index]
                    for row in matrix
                    if col_index < len(row))
            for col_index in range(len(matrix[0]))]

def encode(cleartext):
    if not cleartext: return cleartext
    
    norm_text = normalize(cleartext)
    
    square = split_square(norm_text)
    
    trans_square = transpose(square)
    
    cyphertext = ' '.join(partition(''.join(trans_square), 5))
    
    return cyphertext
    
def decode(cyphertext):
    norm_cyph = normalize(cyphertext)
    num_rows = row_size(norm_cyph)
    num_cols = ceil(len(norm_cyph)/num_rows)
    num_extra = len(norm_cyph)%num_rows

    # for the first num_extra rows, partition by num_cols
    # after that partition by num_cols-1
    split_at = num_cols*num_extra
    extra_rows, regular_rows = norm_cyph[:split_at], norm_cyph[split_at:]
    square = partition(extra_rows, num_cols) + partition(regular_rows, num_cols-1)
    
    trans_square = transpose(square)
    cleartext = ''.join(trans_square)
    return cleartext
