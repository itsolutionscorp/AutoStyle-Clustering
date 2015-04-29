ASCII_A = ord('a')
ASCII_Z = ord('z')
ASCII_ZERO = ord('0')
ASCII_NINE = ord('9')

CIPHER_BLOCK_SIZE = 5

def decode(cipher):
  plain = ''
  for c in cipher.lower():
    if ASCII_A <= ord(c) <= ASCII_Z:
      offset = ord(c) - ASCII_A
      plain += chr(ASCII_Z - offset)
    elif ASCII_ZERO <= ord(c) <= ASCII_NINE:
      plain += c

  return plain

def encode(text):
  cipher = ''
  output = ''
  for c in text.lower():
    if ASCII_A <= ord(c) <= ASCII_Z:
      offset = ord(c) - ASCII_A
      cipher += chr(ASCII_Z - offset)
    elif ASCII_ZERO <= ord(c) <= ASCII_NINE:
      cipher += c

  for i in range(0, len(cipher), CIPHER_BLOCK_SIZE):
     output += cipher[i:i+CIPHER_BLOCK_SIZE] + ' '

  return output.strip()
