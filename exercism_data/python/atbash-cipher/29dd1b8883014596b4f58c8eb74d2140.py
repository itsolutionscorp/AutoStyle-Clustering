def decode(text):
    return atbash(text, True)


def encode(text):
    return atbash(text)


def atbash(text='test', decode=False):
    a, b = 'abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba'
    if decode:
        a, b = b, a
        text = text.replace(' ', '')
    else:
        text = text.replace(' ', '').replace('.', '').replace(',', '').lower()
        text = ' '.join(text[i:i+5] for i in range(0, len(text), 5))

    return text.translate(str.maketrans(a, b))
