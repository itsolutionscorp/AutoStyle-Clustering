from string import ascii_lowercase, digits

ENCODING = str.maketrans(ascii_lowercase + digits,
                         ascii_lowercase[::-1] + digits)


def encode(text):
    return _chunk(_encode(_clean(text)))


def decode(text):
    return _clean(_encode(text))


def _clean(text):
    return "".join([c.lower() for c in text if c.isalnum()])


def _encode(text):
    return text.translate(ENCODING)


def _chunk(text):
    if len(text) <= 5:
        return text
    return text[:5] + " " + _chunk(text[5:])
