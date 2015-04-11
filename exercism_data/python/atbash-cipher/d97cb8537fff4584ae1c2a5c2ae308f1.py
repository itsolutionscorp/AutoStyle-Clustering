def encode(plaintext):

  plaintext = plaintext.lower()
  ciphertext = []

  i = 0
  for letter in plaintext:
    if (i % 5 == 0 and len(ciphertext) > 0 and ciphertext[-1] != " "):
      ciphertext.append(" ")
    ascii_code = ord(letter)
    if (ascii_code > 47 and ascii_code < 58):
      ciphertext.append(letter)
    elif (ascii_code > 96 and ascii_code < 123):
      ciphertext.append(chr(122 - (ascii_code - 97)))
    else:
      continue
    i += 1

  return "".join(ciphertext).strip()


def decode(ciphertext):

  ciphertext = ciphertext.lower()
  plaintext = []

  for letter in ciphertext:
    ascii_code = ord(letter)
    if (ascii_code > 96 and ascii_code < 123):
      plaintext.append(chr(122 - (ascii_code - 97)))
    else:
      continue

  return "".join(plaintext).strip()
