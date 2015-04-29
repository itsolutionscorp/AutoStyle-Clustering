import math
from itertools import chain, izip_longest

def encode(msg):
    normalized_msg = normalize(msg)
    len_norm_msg = len(normalized_msg)
    num_cols, num_rows = calc_dimensions(len_norm_msg)
    rows = ''.join([''.join(normalized_msg[offset::num_cols]) for offset in range(num_cols)])
    return ' '.join(rows[offset:offset + 5] for offset in range(0, len(rows), 5))

def decode(msg):
    normalized_msg = normalize(msg)
    len_norm_msg = len(normalized_msg)
    num_rows, num_cols = calc_dimensions(len_norm_msg)
    last_col_size = (num_cols * num_rows) - len_norm_msg
    short_rows_start = num_rows - last_col_size

    full_size_rows = [''.join(normalized_msg[row*num_cols:(row + 1) * num_cols])
                                            for row in range(short_rows_start)]
    short_rows = [''.join(normalized_msg[row*(num_cols - 1):(row + 1) * (num_cols - 1)])
                                            for row in range(short_rows_start + 1, num_rows + 1)]
    full_size_cols = [[row[i] for row in full_size_rows] for i in range(num_cols)]
    short_cols = [[row[i] for row in short_rows] for i in range(num_cols - 1)]
    return ''.join([''.join(list(chain(full, short))) for full, short in
                                                izip_longest(full_size_cols, short_cols,fillvalue='')])

def normalize(msg):
    return [c.lower() for c in msg if c.isalnum()]

def calc_dimensions(length):
    columns = int(math.ceil(math.sqrt(length)))
    if columns * columns == length:
        return columns, columns
    else:
        return columns, int(math.ceil(float(length) / columns))
