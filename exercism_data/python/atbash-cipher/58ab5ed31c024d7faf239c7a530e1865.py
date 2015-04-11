ALPHA_FORWARD  = 'abcdefghijklmnopqrstuvwxyz'
ALPHA_BACKWARD = 'zyxwvutsrqponmlkjihgfedcba'


def clean(phrase):
    return ''.join([ch for ch in phrase.lower() if ch.isalnum()])


def decode(phrase):
    return clean(phrase).translate(str.maketrans(ALPHA_BACKWARD, ALPHA_FORWARD))


def encode(phrase):
    ret = clean(phrase).translate(str.maketrans(ALPHA_FORWARD, ALPHA_BACKWARD))
    if len(phrase) > 5:
        ret = ' '.join(ret[i:i + 5] for i in range(0, len(ret), 5))
    return ret
