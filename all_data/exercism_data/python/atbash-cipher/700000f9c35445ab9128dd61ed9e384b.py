__author__ = 'agupt15'

PLAIN = 'abcdefghijklmnopqrstuvwxyz'
CIPHER = 'zyxwvutsrqponmlkjihgfedcba'
CIPHER_MAP = {p: c for (p, c) in zip(PLAIN, CIPHER)}
REVERSE_CIPHER_MAP = {c: p for (p, c) in zip(PLAIN, CIPHER)}

PUNCTUATION = '.,;:!'
GROUP_SIZE = 5


def decode(st):
    return ''.join([REVERSE_CIPHER_MAP[ch] for ch in st])


def encode(st):
    st = st.strip().lower()
    encoded = []
    for ch in st:
        if ch in CIPHER_MAP:
            encoded.append(CIPHER_MAP[ch])
        elif ch not in PUNCTUATION and ch.strip() is not '':
            encoded.append(ch)
    partition = [encoded[i:i + GROUP_SIZE] for i in range(0, len(encoded), GROUP_SIZE)]
    return ' '.join([''.join(elem) for elem in partition])
