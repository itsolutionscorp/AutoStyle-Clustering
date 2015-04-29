EN_ALPHABET = list('abcdefghijklmnopqrstuvwxyz')


def _grouping(text: str, grouping=5) -> str:
    r = ''
    for idx, char in enumerate(text):
        if idx and not idx % grouping:
            r += ' '
        r += char
    return r


def _transform(text: str) -> str:
    length = len(EN_ALPHABET)
    cipher = ''

    for char in text:
        char_lower = char.lower()
        if char_lower in EN_ALPHABET:
            cipher += EN_ALPHABET[length - EN_ALPHABET.index(char_lower) - 1 % length]
        elif char.isnumeric():
            cipher += char

    return cipher


def decode(text: str) -> str:
    return _transform(text)


def encode(text: str) -> str:
    return _grouping(_transform(text))
