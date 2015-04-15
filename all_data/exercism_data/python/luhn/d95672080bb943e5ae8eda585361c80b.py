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
		num[-2::-2] = [2*x if 2*x < 10 else 2*x-9 for x in num[-2::-2]]
		return num

	def checksum(self):
		return sum(self.addends())% 10

	def is_valid(self):
		return self.checksum() == 0

	@staticmethod
	def create(num):
		new_num = num * 10
		checksum = Luhn(new_num).checksum()
		return new_num if checksum == 0 else new_num + 10 - checksum
