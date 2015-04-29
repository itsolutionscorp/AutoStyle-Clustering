def encode(text):
    text = ''.join([c.lower() for c in text if c.isalnum()])
    text = text.translate(get_cipher())
    text = [text[i:i+5] for i in range(0, len(text), 5)]

    return ' '.join(text)


def decode(text):
    cipher = get_cipher(decode=True)
    return ''.join(text.translate(cipher).split())


def get_cipher(decode=False):
    key = [ 'abcdefghijklmnopqrstuvwxyz',
            'zyxwvutsrqponmlkjihgfedcba']

    return decode and str.maketrans(key[1], key[0]) \
                   or str.maketrans(key[0], key[1])
