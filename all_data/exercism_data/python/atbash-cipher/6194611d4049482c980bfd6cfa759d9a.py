from string import whitespace, punctuation
_d = dict(zip([ord(c) for c in 'abcdefghijklmnopqrstuvwxyz'],
    [ord(c) for c in 'zyxwvutsrqponmlkjihgfedcba']))

def encode(msg):
    return(_apply_cipher(_block_txt(_strip_msg(msg))))

def decode(msg):
    return(_apply_cipher(_strip_msg(msg)))

def _apply_cipher(s):
    return(s.translate(_d))

def _strip_msg(s):
    return(''.join(filter(lambda c: c not in punctuation and c not in whitespace, s.lower())))

def _block_txt(s):
    l = list(s)
    block_size = 5
    i = 0
    while block_size*i < len(l):
        l.insert(i+block_size+i*block_size, ' ')
        i += 1
    return(''.join(l).strip())
