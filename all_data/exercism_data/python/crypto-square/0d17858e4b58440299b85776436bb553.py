from itertools import zip_longest


def strip(msg):
    return ''.join(ch for ch in msg if ch.isalnum()).lower()


def encode(msg):
    msg = strip(msg)
    if msg == '':
        return msg

    ret = ''
    dist = int(len(msg) ** 0.5)

    if dist ** 2 != len(msg):
        dist += 1

    for row in list(zip_longest(*in_pieces(msg, dist), fillvalue='')):
        ret += ''.join(row)
    return ' '.join(in_pieces(ret, 5))


def decode(msg):
    ret = ''
    msg = strip(msg)
    sqr = int(len(msg) ** 0.5)
    dist = sqr
    if dist * dist < len(msg):
        dist += 1

    cnt_partial, cnt_full = divmod(len(msg), dist)
    split_at = (cnt_partial + 1) * cnt_full

    columns = in_pieces(msg[:split_at], sqr) \
              + in_pieces(msg[split_at:], sqr - 1, ' ')
    for row in list(zip_longest(*columns, fillvalue='')):
        ret += ''.join(row)
    return ret.strip()


def in_pieces(msg, n, append=''):
    if msg == '':
        return append
    return [msg[i:i + n] + append for i in range(0, len(msg), n)]
