import string

trans_dict = dict(zip(string.lowercase,
                    reversed(string.lowercase)))

def encode(plain):

    preprocessed = _preprocess(plain)
    converted = _convert(preprocessed)
    encoded = _insert_spaces(converted, every = 5)

    return encoded


def decode(cipher):

    preprocessed = _preprocess(cipher)
    decoded = _convert(preprocessed)

    return decoded


def _preprocess(text):

    return ''.join(ch for ch in text if ch.isalnum()).lower()


def _convert(text):

    converted = ''

    for ch in text: converted += trans_dict.get(ch, ch)

    return converted


def _insert_spaces(text, every = 5):

    return ' '.join(text[i:i+every] for i in xrange(0, len(text), every))
