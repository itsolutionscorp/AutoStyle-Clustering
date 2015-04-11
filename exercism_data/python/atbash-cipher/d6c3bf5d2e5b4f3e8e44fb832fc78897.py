from string import ascii_lowercase, punctuation, whitespace
atbash_length = 5

def encode(text):
    text = text.lower()
    text = text.translate(str.maketrans(ascii_lowercase,
                                        ascii_lowercase[::-1],
                                        punctuation + whitespace))
    text_enc = text[:atbash_length]
    remainder = text[atbash_length:]

    while remainder:
        text_enc = ' '.join([text_enc, remainder[:atbash_length]])
        remainder = remainder[atbash_length:]
    
    return text_enc

def decode(cipher):
    cipher_dec = cipher.translate(str.maketrans(ascii_lowercase,
                                                ascii_lowercase[::-1],
                                                whitespace))
    return cipher_dec
