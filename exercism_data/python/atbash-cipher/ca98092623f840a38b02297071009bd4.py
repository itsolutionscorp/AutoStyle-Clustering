from re import findall
from string import ascii_lowercase, digits

_plain_alphabet = ascii_lowercase + digits
_cipher_alphabet = ascii_lowercase[::-1] + digits

_encode_map = dict(zip(_plain_alphabet, _cipher_alphabet))
_decode_map = dict(zip(_cipher_alphabet, _plain_alphabet))

def encode(msg):
    ciphertext = "".join(_encode_map[c] for c in msg.lower() if c in _plain_alphabet)
    # Tests demand a space every 5 letters, why is unclear.
    return " ".join(findall(".{1,5}", ciphertext))

def decode(msg):
    return "".join(_decode_map[c] for c in msg if c in _cipher_alphabet)
