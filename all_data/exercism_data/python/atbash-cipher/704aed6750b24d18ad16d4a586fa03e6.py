from string import ascii_lowercase, maketrans

CIPHER_BLOCK_SIZE = 5
CIPHER_KEY = maketrans(ascii_lowercase, ascii_lowercase[::-1])

def encode(text):
    cipher = ''
    output = ''

    for c in text.lower():
      if c.isalnum():
        cipher += c.translate(CIPHER_KEY)

    for i in range(0, len(cipher), CIPHER_BLOCK_SIZE):
      output += cipher[i:i + CIPHER_BLOCK_SIZE] + ' '

    return output.strip()

def decode(cipher):
    message = ''
    for c in cipher.lower():
      if c.isalnum():
        message += c.translate(CIPHER_KEY)

    return message
