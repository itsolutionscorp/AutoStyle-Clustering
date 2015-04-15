import string

class Cipher(object):
  def __init__(self, key='a'):
    self.key = key.lower()
    self.alphaKey = list(string.ascii_lowercase)
    
  def getCode(self, string, mode, keyCode=[0]):
    code1 = []
    code2 = []

    for char in string:
      if char in self.alphaKey:
	code1.append(self.alphaKey.index(char))
	  
    strLen = len(string)
    keyLen = len(keyCode)
    if strLen > keyLen:
      diff = strLen - keyLen
      for d in range(diff):
	keyCode.append(0)
     
    for num in range(len(code1)):
      if mode == 'encrypt':
	i = code1[num] + keyCode[num]
      if mode == 'decrypt':
	i = code1[num] - keyCode[num]
      if i > 25:
	i -= 26
      if i < 0:
	i = 26 - abs(i)
      code2.append(i)
	
    return code2
    
  def convertToString(self, code2):
    encString = ''
    for num in code2:
      encString += self.alphaKey[num]
    return encString

  def encode(self, string):
    keyCode = []
    for k in self.key:
      if k in self.alphaKey:
	keyCode.append(self.alphaKey.index(k))
    string = string.lower()
    code2 = self.getCode(string, 'encrypt', keyCode)
    return self.convertToString(code2)
  
  def decode(self, string):
    keyCode = []
    for k in self.key:
      if k in self.alphaKey:
	keyCode.append(self.alphaKey.index(k))
    string = string.lower()
    code2 = self.getCode(string, 'decrypt', keyCode)
    return self.convertToString(code2)
  
class Caesar(Cipher):
  def __init__(self):
    super(Caesar, self).__init__(key=('d' * 500))
