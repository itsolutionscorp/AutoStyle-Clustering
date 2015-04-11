class Caesar(object):
  global alphaKey
  alphaKey = {'a':0, 'b':1, 'c':2, 'd':3, 'e':4, 'f':5, 
	      'g':6, 'h':7, 'i':8, 'j':9, 'k':10, 'l':11,
	      'm':12, 'n':13, 'o':14, 'p':15, 'q':16, 'r':17,
	      's':18, 't':19, 'u':20, 'v':21, 'w':22, 'x':23,
	      'y':24, 'z':25}
  
  def getCode(self, string, mode):
    global alphaKey
    code1 = []
    code2 = []

    for char in string:
      if char in alphaKey:
	code1.append(alphaKey[char])
    for num in code1:
      if mode == 'encrypt':
	i = num + 3
      if mode == 'decrypt':
	i = num - 3
      if i > 25:
	i -= 26
      code2.append(i)
    return code2
  
  def convertToString(self, code2):
    global alphaKey
    encString = ''
    for num in code2:
      for obj in alphaKey:
	if alphaKey[obj] == num:
	  encString += obj
    return encString
    
  
  def encode(self, string):
    string = string.lower()
    code2 = self.getCode(string, 'encrypt')
    return self.convertToString(code2)
  
  def decode(self, string):
    string = string.lower()
    code2 = self.getCode(string, 'decrypt')
    return self.convertToString(code2)

class Cipher(object):
  def __init__(self, key='a'):
    self.key = key.lower()
    self.alphaKey = {'a':0, 'b':1, 'c':2, 'd':3, 'e':4, 'f':5,
		     'g':6, 'h':7, 'i':8, 'j':9, 'k':10, 'l':11,
		     'm':12, 'n':13, 'o':14, 'p':15, 'q':16, 'r':17,
		     's':18, 't':19, 'u':20, 'v':21, 'w':22, 'x':23,
		     'y':24, 'z':25}
    
  def getCode(self, string, mode, keyCode=[0]):
    global alphaKey
    code1 = []
    code2 = []

    for char in string:
      if char in self.alphaKey:
	code1.append(self.alphaKey[char])
	  
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
    global alphaKey
    encString = ''
    for num in code2:
      for obj in alphaKey:
	if alphaKey[obj] == num:
	  encString += obj
    return encString

  def encode(self, string):
    keyCode = []
    for k in self.key:
      if k in self.alphaKey:
	keyCode.append(self.alphaKey[k])
    
    string = string.lower()
    code2 = self.getCode(string, 'encrypt', keyCode)
    return self.convertToString(code2)
  
  def decode(self, string):
    keyCode = []
    for k in self.key:
      if k in self.alphaKey:
	keyCode.append(self.alphaKey[k])
    
    string = string.lower()
    code2 = self.getCode(string, 'decrypt', keyCode)
    return self.convertToString(code2)
