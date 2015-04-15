from string import maketrans
import re

cipher = maketrans(
    "abcdefghijklmnopqrstuvwxyz",
    "zyxwvutsrqponmlkjihgfedcba")

def encode(plain_text):
    encoded = _apply_cipher(plain_text)
    groups = [ encoded[i:i + 5] for i in range(0, len(encoded), 5) ]
    return " ".join(groups)

def decode(encoded_text):
    return _apply_cipher(encoded_text)

def _apply_cipher(text):
    stripped = re.sub('[^a-z0-9]+', '', text.lower())
    return stripped.translate(cipher);
