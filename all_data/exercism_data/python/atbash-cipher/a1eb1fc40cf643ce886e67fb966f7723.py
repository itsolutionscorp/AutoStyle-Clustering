#
#
#

import string

def encode(phrase):
    # Remove punctuation and whitespace
    phrase = phrase.translate(string.maketrans('',''),string.punctuation + string.whitespace)
    phrase = phrase.lower()
    enc_phrase = ''
    for idx in range(0,len(phrase)):
        # Add spaces
        if (idx % 5 == 0) and (idx > 0):
            enc_phrase = enc_phrase + ' '
        # Encode
        enc_phrase = enc_phrase + cipher(phrase[idx])
    return enc_phrase

def decode(phrase):
    # Remove punctuation and whitespace
    phrase = phrase.translate(string.maketrans('',''),string.punctuation + string.whitespace)
    phrase = phrase.lower()
    dec_phrase = ''
    for char in phrase:
        dec_phrase = dec_phrase + cipher(char)
    return dec_phrase

def cipher(char):
    # Returns the encrypted version of the input char
    if char.isdigit():
        return char
    else:
        num = ord(char) % 97
        enc = chr((-(num + 1) % 26) + 97)
        return enc




