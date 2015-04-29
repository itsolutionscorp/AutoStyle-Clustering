import string

alpha = string.ascii_lowercase
cipher = string.ascii_lowercase[::-1]

def decode(ciphertext):
    decoded_string = ciphertext.translate(str.maketrans(cipher, alpha, string.whitespace))
    return decoded_string

def encode(plaintext):
    coded_string = plaintext.lower().translate(str.maketrans(alpha, cipher, string.whitespace + string.punctuation))
    return ' '.join(coded_string[idx: idx + 5] for idx in range(0, len(plaintext), 5)).rstrip()
