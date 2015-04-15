'''
Take a number and determine whether or not it is valid per the Luhn formula
'''
class Luhn:
	
	def __init__(self, id_num):
		self.id_num = id_num
		
	def addends(self):
		return [int(digit) if ii%2==0 else 2*int(digit) if 2*int(digit)<10 else 2*int(digit)-9
			for ii,digit in enumerate(str(self.id_num)[-1::-1])][-1::-1]
		
	def checksum(self):
		return sum(self.addends()) % 10
		
	def is_valid(self):
		return 0 == sum(self.addends()) % 10
	
	def create(id_to_complete):
		return 10*id_to_complete + (10-Luhn(10*id_to_complete).checksum())%10
		
if __name__ == '__main__':
	print(Luhn(123).addends())
	print(Luhn.create(123))
