from string import whitespace, digits, punctuation
def cycle(num, top):
	if num <= top: return num
	newnum = num
	while newnum > top:
		newnum -= top+1
	return newnum

class Cipher:
	def __init__(self, code = 'd'):
		self.code = code
	def encode(self, message):
		mess = ''.join([i for i in message if not (i.isdigit() or i in punctuation or i in whitespace)]).lower()
		result = ''
		letters = 'abcdefghijklmnopqrstuvwxyz'
		for x in range(len(mess)):
			result += letters[cycle(letters.index(mess[x]) + letters.index(self.code[cycle(x, len(self.code)-1)]),25)]
		return result
	def decode(self, message):
		result = ''
		letters = 'abcdefghijklmnopqrstuvwxyz'
		for x in range(len(message)):
			result += letters[cycle(letters.index(message[x]) - letters.index(self.code[cycle(x, len(self.code)-1)]),25)]
		return result
class Caesar(Cipher):
	def __init__(self):
		Cipher.__init__(self, 'd')
