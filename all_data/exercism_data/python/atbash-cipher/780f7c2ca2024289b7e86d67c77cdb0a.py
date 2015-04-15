import re

def decode(ciphertext):
    plaintext = ''
    for c in list(ciphertext):
        if c.isalpha():
            tmp = ord('z') - ord(c)
            plaintext += chr(ord('a') + tmp)
        elif c.isdigit():
            plaintext += c

    return plaintext

def encode(plaintext):
    ciphertext = ''
    temptext = ''
    cs = list(plaintext.lower())
    for c in cs:
        if c.isalpha():
            tmp = ord(c) - ord('a')
            temptext += chr(ord('z') - tmp)
        elif c.isdigit():
            temptext += c
    if len(temptext) > 5:
        i = 0
        for i in range(0, len(temptext) - 5, 5):
            ciphertext += temptext[i:i+5] + ' '
        ciphertext += temptext[i+5:]
    else:
        ciphertext = temptext
    return ciphertext
