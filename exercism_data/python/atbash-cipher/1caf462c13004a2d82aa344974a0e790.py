import string

def decode(text):
    return atbash(text, True)


def encode(text):
    return atbash(text)


def atbash(text='test', decode=False):
    a, b = 'abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba'
    if decode:
        a, b = b, a

    text = text.lower().translate(str.maketrans(a, b, string.punctuation + string.whitespace))

    if not decode:
        text = ' '.join(text[i:i+5] for i in range(0, len(text), 5))

    return text
