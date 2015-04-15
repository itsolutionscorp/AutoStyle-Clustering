from copy import copy

ALPHABET = list('abcdefghijklmnopqrstuvwxyz')
REV_ALPHABET = copy(ALPHABET)
REV_ALPHABET.reverse()
NUMBERS = list('0123456789')

def decode(ciphertext):
    output_chars = _transform(ciphertext, REV_ALPHABET)
    return ''.join(output_chars)

def encode(plaintext):
    output_chars = _transform(plaintext, ALPHABET)
    return _inject_spaces(output_chars)

def _inject_spaces(ciphertext):
    spaced_cipher = []
    for (i, x) in enumerate(ciphertext):
        if i % 5 == 0 and i != 0:
            spaced_cipher.append(' ')
        spaced_cipher.append(x)
    return ''.join(spaced_cipher)

def _transform(text, char_set):
    output_chars = []
    for x in text.lower():
        if x in char_set:
            char_position = len(char_set) - char_set.index(x) - 1
            output_chars.append(char_set[char_position])
        elif x in NUMBERS:
            output_chars.append(x)
    return output_chars
