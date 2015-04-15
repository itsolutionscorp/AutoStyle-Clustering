from string import maketrans, ascii_lowercase, punctuation, whitespace


table = maketrans(ascii_lowercase, ascii_lowercase[::-1])


def decode(text):
    return text.translate(table, whitespace)


def encode(text):
    text = text.lower().translate(table, punctuation+whitespace)
    return ' '.join([text[x:x+5] for x in range(0, len(text), 5)])
