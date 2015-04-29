# represents the Luhn checksum of the given number
class Luhn(object):
	def __init__(self,n):
		self.number=n

	# separates the digits of the number
	def digits(self):
		answer=[]
		n=self.number
		while n>0:
			n,m=divmod(n,10)
			answer=[m]+answer
		return answer

	# doubles every other digit, subtracting 9 from those over 9
	def addends(self):
		digits=self.digits()
		if len(digits)<2:
			return digits
		for i in range(len(digits)-2,-1,-2):
			digits[i]*=2
			if digits[i]>9:
				digits[i]-=9
		return digits

	# calculates the Luhn checksum
	def checksum(self):
		return sum(self.addends())%10

	# determines whether or not this number is a valid Luhn number
	def is_valid(self):
		return self.checksum()==0

	# creates a luhn number from the given partial number
	@classmethod
	def create(cls,n):
		answer=cls(n*10)
		return answer.number+((-answer.checksum())%10)
