'''exer crypto_square'''

from math import sqrt, ceil, floor


def calc_rows(num):
    '''return number of rows needed for closest square that can contain num'''
    return int(floor(sqrt(num)))
def calc_cols(num):
    '''return number of cols needed for closest square that can contain num'''
    return int(ceil(sqrt(num)))
def normalize(buf):
    '''return lower case version of only alpha numeric characters'''
    norm = ''
    for char in [x for x in buf if x.isalnum()]:
        norm += char
    return norm.lower()
def chunk_by_n(buf, num):
    """A generator to create chunks of len n"""
    while buf:
        yield buf[:num]
        buf = buf[num:]

def encode(plain):
    '''encode plain into cipher block'''
    cipher = ''
    scrubbed = normalize(plain)
    sm_square = calc_cols(len(scrubbed))
    square = [list(chunk) for chunk in chunk_by_n(scrubbed, sm_square)]
    for row in square:
        print row

    count = 1
    while square:
        for row in square:
            try:
                cipher += row.pop(0)
                if count % 5 == 0:
                    cipher += ' '
                count += 1
            except IndexError:  # row is empty, so filter it out for next run
                square = [x for x in square if x]

    return cipher.strip()

def decode(cipher):
    '''decode cipher block into plain text'''
    cipher = normalize(cipher)
    clen = len(cipher)
    sm_square = calc_cols(clen)
    cols = clen / sm_square
    num_full_cols = clen % sm_square

    marker = (cols + 1) * num_full_cols  # where the full cols end & part start
    full_cols_s = cipher[:marker]
    part_cols_s = cipher[marker:]
    last_row_s = full_cols_s[cols::cols + 1]

    trimmed_full_cols = [chunk[:-1] for chunk in chunk_by_n(full_cols_s,
                                                            cols + 1)]
    part_cols = [chunk for chunk in chunk_by_n(part_cols_s, cols)]
    normalized_cols_s = ''.join(trimmed_full_cols + part_cols)
    full_rows = [normalized_cols_s[ndx::cols] for ndx in range(cols)]
    plain = ''.join(full_rows + [last_row_s])
    return plain
