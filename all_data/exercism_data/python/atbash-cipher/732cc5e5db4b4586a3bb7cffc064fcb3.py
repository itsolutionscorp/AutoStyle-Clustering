from string import (maketrans, translate, ascii_letters, ascii_lowercase,
                    punctuation)

atbash_cipher_trans = maketrans(ascii_letters, ascii_lowercase[::-1] * 2)


def encode(msg):
    # Puts message in lower case, translates it
    # and removes the whitespace and punctuation.
    msg = msg.translate(atbash_cipher_trans, " " + punctuation)

    # Formats the string into 5-blocks and returns
    return " ".join([msg[i:i+5] for i in range(0, len(msg), 5)])


def decode(msg):
    return msg.translate(atbash_cipher_trans, " " + punctuation)
