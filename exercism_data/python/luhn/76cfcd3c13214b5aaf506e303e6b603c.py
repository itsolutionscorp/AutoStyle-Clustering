'''
Take a number and determine whether or not it is valid per the Luhn formula
'''
class Luhn:
	
	def __init__(self, id_num):
		self.id_num = id_num
		
	def addends(self):
		digits = [int(digit) for digit in str(self.id_num)]
		digits[-2::-2] = [2*digit if 2*digit<10 else 2*digit-9 for digit in digits[-2::-2]]
		return digits
		
	def checksum(self):
		return sum(self.addends()) % 10
		
	def is_valid(self):
		return 0 == sum(self.addends()) % 10
	
	@staticmethod
	def create(id_to_complete):
		return 10*id_to_complete + (10-Luhn(10*id_to_complete).checksum())%10
		
if __name__ == '__main__':
	print(Luhn(123).addends())
	print(Luhn.create(123))
