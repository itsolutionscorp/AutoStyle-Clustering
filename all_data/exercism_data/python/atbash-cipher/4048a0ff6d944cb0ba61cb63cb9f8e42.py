import string

from unicodedata import category


atbash = lambda alphabet: str.maketrans(dict(zip(alphabet, reversed(alphabet))))


def _split_every(n: int, seq: iter):
    for i in range(0, len(seq), n):
        yield seq[i:i+n]


def decode(s: str, alphabet=string.ascii_lowercase) -> str:
    cipher = atbash(alphabet)
    cipher_text = s.replace(" ", "")
    return cipher_text.translate(cipher)


def encode(s: str, alphabet=string.ascii_lowercase, word_size=5) -> str:
    cipher = atbash(alphabet)
    cipher_text = (s.lower()
                    .replace(" ", "")
                    .translate(cipher))
    cipher_text = "".join(ch for ch in cipher_text if category(ch)[0] != "P")
    return " ".join(_split_every(word_size, cipher_text))
