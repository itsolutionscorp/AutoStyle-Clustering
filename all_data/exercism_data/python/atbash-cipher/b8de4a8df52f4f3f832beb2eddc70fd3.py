from string import ascii_lowercase, punctuation


def insert_spaces(sentence, length=5):
    return ' '.join(sentence[i:i+length] for i in range(0, len(sentence), length))


def decode(msg):
    dec_translate = str.maketrans(''.join(reversed(ascii_lowercase)), ascii_lowercase)
    return msg.replace(' ', '').translate(dec_translate)


def encode(msg):
    msg = ''.join(char.lower().replace(' ', '') for char in msg if char not in punctuation)
    enc_translate = str.maketrans(ascii_lowercase, ''.join(reversed(ascii_lowercase)))
    return insert_spaces(msg.translate(enc_translate))
