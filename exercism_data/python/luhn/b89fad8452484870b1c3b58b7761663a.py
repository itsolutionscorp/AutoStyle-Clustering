class Luhn:

	def __init__(self, n = 0):
		self.n = n

	def checksum(self):
		return sum(Luhn(self.n).addends()) % 10

	def addends(self):
		flipped_number = reversed(list(str(self.n)))
		flipped_number = map(int, flipped_number)		
		converted_list = []
		for digit in range(len(flipped_number)):
			if digit % 2 <> 0:	
				if 2*flipped_number[digit] >= 10:		
					converted_list += [2*flipped_number[digit] - 9]
				else:
					converted_list += [2*flipped_number[digit]]
			else:
				converted_list += [flipped_number[digit]]
		return converted_list[::-1]

	def is_valid(self):
		return Luhn.checksum(self) == 0

	@staticmethod
	def create(n):
		testnumber = n*10
		if Luhn(testnumber).is_valid():
			return testnumber
		else:
			return testnumber + 10 - Luhn(testnumber).checksum()
