# -*- coding: utf-8 -*-
import re

def decode(ciphertext):
    codepoint_a, codepoint_z = ord('a'), ord('z')
    changer = codepoint_a + codepoint_z

    source = ''
    target = ''
    for x in range(codepoint_a, codepoint_z + 1):
        source += chr(x)
        target += chr(changer - x)

    ciphertext = ciphertext.replace(' ', '')

    return ciphertext.translate(ciphertext.maketrans(source, target))

def encode(plaintext):
    ciphertext = re.sub('[.,]', '', plaintext.lower())
    ciphertext = decode(ciphertext)
    text_list = [ciphertext[i:i+5] for i in range(0,len(ciphertext),5)]

    return ' '.join(text_list)
