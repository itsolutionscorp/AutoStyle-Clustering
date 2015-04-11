import string
import random

class Cipher(object):
  def __init__(self, key = None):
    if (key == None):
      self.key = ''.join(random.SystemRandom().choice(string.ascii_lowercase) for _ in range(100))
    else:
      self.key = key

  def encode(self, cleartext):
    return ''.join(list(map(self.__encodeChar, self.__getMessage(cleartext))))

  def decode(self, ciphertext):
    return ''.join(list(map(self.__decodeChar, self.__getMessage(ciphertext))))

  def __encodeChar(self, tpl):
    value = ord(tpl[0]) + self.__offset(tpl[1])
    if (value > ord('z')):
      value = value - 26
    return chr(value)

  def __decodeChar(self, tpl):
    value = ord(tpl[0]) - self.__offset(tpl[1])
    if (value < ord('a')):
      value = value + 26
    return chr(value)

  def __offset(self, char):
    return (ord(char) - ord('a'))

  def __getKey(self, length):
    result = self.key
    while (len(result) < length):
      result += result
    return result

  def __getMessage(self, text):
    cleantext = ''.join([c for c in text if str.isalpha(c)]).lower()
    return zip(cleantext, self.__getKey(len(cleantext)))

class Caesar(Cipher):
  def __init__(self):
    Cipher.__init__(self, "ddddddddddddddddd")
