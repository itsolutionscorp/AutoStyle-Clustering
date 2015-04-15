__author__ = 'emiller42'

import string


def transpose(letter):
    letters = string.ascii_lowercase
    if letter in letters:
        return letters[abs(25 - letters.find(letter))]
    if letter in string.digits:
        return letter
    return ''


def decode(cipher_text):
    cipher_text = cipher_text.lower()
    plain_text = ''
    for letter in cipher_text:
        plain_text += transpose(letter)
    return plain_text


def encode(plain_text):
    plain_text = plain_text.lower()
    cipher_text = ''
    i = 0
    for letter in plain_text:
        if letter not in string.whitespace + string.punctuation:
            if i > 0 and i % 5 == 0:
                cipher_text += ' '
            cipher_text += transpose(letter)
            i += 1
    return cipher_text
