'''luhn.py
	created 16 Oct 2014
	by @jestuber 
	Check if a number is valid per the Luhn formula'''


class Luhn(object):
	"""docstring for Luhn"""
	def __init__(self, num = 0):
		super(Luhn, self).__init__()
		self.num = num
		
	def addends(self):
		num = [int(i) for i in str(self.num)]
		odd = num[-1::-2]
		even = num[-2::-2]
		even = [2*x if 2*x < 10 else 2*x-9 for x in even]

		result = [None]*(len(even)+len(odd))
		result[::2] = odd
		result[1::2] = even
		return result[::-1]

	def checksum(self):
		checksum = sum(self.addends())
		return checksum % 10

	def is_valid(self):
		return self.checksum() == 0

	@staticmethod
	def create(num):
		luhn = Luhn(num*10)
		checksum = luhn.checksum()
		return num*10 if checksum == 0 else num*10+10-checksum
