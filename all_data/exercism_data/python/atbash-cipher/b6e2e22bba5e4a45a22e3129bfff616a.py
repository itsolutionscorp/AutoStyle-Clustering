import textwrap

plain = 'abcdefghijklmnopqrstuvwxyz0123456789'
cipher = 'zyxwvutsrqponmlkjihgfedcba0123456789'
decodeMap = str.maketrans(plain, cipher)


def decode(encryptedMsg):
    return ''.join([part.translate(decodeMap) for part in encryptedMsg.lower().split()])


def encode(msg):
    stripped_msg = ''.join(list(filter(str.isalnum, msg))).lower()
    return ' '.join(textwrap.wrap(stripped_msg.translate(decodeMap), 5))
