from string import maketrans

class Caesar(object):

  def __init__(self):
    self.plain = "abcdefghijklmnopqrstuvwxyz"
    self.ciph = "defghijklmnopqrstuvwxyzabc"

  def is_alpha(self, letter):
    return ord(letter) > 96 and ord(letter) < 123

  def encode(self, plaintext):
    return filter(self.is_alpha, plaintext.lower()).translate(maketrans(self.plain, self.ciph))

  def decode(self, ciphertext):
    return ciphertext.translate(maketrans(self.ciph, self.plain))


class Cipher(object):

  def __init__(self, key = ""):
    self.key = key

  def encode(self, plaintext):
    if (len(self.key) == 0):
      return plaintext
    if (len(self.key) < len(plaintext)):
      self.key = self.key * (len(plaintext) / len(self.key) + 1)
    ciphertext = [ chr(ord(z[0]) + ord(z[1]) - 97) if ord(z[0]) + ord(z[1]) - 97 < 123 else chr(ord(z[0]) + ord(z[1]) - 123) for z in zip (plaintext, self.key) ]
    return "".join(ciphertext)
     
  def decode(self, ciphertext):
    if (len(self.key) == 0):
      return ciphertext
    if (len(self.key) < len(ciphertext)):
      self.key = self.key * (len(ciphertext) / len(self.key) + 1)
    plaintext = [ chr(ord(z[0]) - ord(z[1]) + 97) if ord(z[0]) - ord(z[1]) + 97 > 96 else chr(ord(z[0]) - ord(z[1]) + 123) for z in zip (ciphertext, self.key) ]
    return "".join(plaintext)
