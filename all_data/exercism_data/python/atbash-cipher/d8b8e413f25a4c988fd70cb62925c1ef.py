from string import ascii_letters, ascii_lowercase, ascii_uppercase

def reverse(s):
    return ''.join(reversed(s))

CIPHER=str.maketrans(ascii_letters,
                     reverse(ascii_lowercase) + reverse(ascii_uppercase))

def decode(ciphertext):
    return ciphertext.translate(CIPHER)

encode = decode
