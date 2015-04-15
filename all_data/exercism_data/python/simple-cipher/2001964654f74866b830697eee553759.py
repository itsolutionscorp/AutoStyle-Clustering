from string import ascii_lowercase
from random import SystemRandom

class Cipher(object):
  def __init__(self, key = None):
    if (key == None):
      self.key = ''.join(SystemRandom().choice(ascii_lowercase) for _ in range(100))
    else:
      self.key = key

  def encode(self, cleartext):
    return ''.join([Cipher.__encodeChar(c) for c in Cipher.__getChars(self.key, cleartext)])

  def decode(self, ciphertext):
    return ''.join([Cipher.__decodeChar(c) for c in Cipher.__getChars(self.key, ciphertext)])

  def __encodeChar(c):
    value = ord(c[0]) + Cipher.__offset(c[1])
    if (value > ord('z')):
      value = value - 26
    return chr(value)

  def __decodeChar(c):
    value = ord(c[0]) - Cipher.__offset(c[1])
    if (value < ord('a')):
      value = value + 26
    return chr(value)

  def __offset( char):
    return (ord(char) - ord('a'))

  def __getChars(key, text):
    cleantext = ''.join([c for c in text if str.isalpha(c)]).lower()
    while (len(key) < len(cleantext)):
      key += key
    return zip(cleantext, key)

class Caesar(Cipher):
  def __init__(self):
    Cipher.__init__(self, "d")
