from string import ascii_lowercase, maketrans

CIPHER_BLOCK_SIZE = 5
CIPHER_KEY = maketrans(ascii_lowercase, ascii_lowercase[::-1])

def transpose(text):
    transposed = ''
    for c in text.lower():
      if c.isalnum():
        transposed += c.translate(CIPHER_KEY)

    return transposed

def encode(text):
    cipher = transpose(text)

    return " ".join([cipher[i:i + CIPHER_BLOCK_SIZE]
                     for i in range(0, len(cipher), CIPHER_BLOCK_SIZE)])

def decode(cipher):
    return transpose(cipher)
