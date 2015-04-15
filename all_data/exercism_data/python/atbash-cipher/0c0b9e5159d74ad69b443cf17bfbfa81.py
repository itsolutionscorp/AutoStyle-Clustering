from string import ascii_lowercase, punctuation, whitespace


_cipher = str.maketrans(ascii_lowercase, "".join(reversed(ascii_lowercase)))
_cleaning = str.maketrans("", "", punctuation + whitespace)


def decode(text):
    return "".join(text.translate(_cipher).split())


def encode(text):
    decoded = text.lower().translate(_cleaning).translate(_cipher)
    return " ".join(decoded[i:i+5] for i in range(0, len(decoded), 5))
