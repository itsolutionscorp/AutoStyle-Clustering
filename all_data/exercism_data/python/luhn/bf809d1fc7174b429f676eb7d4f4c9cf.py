class Luhn():
	
	def __init__(self, num):
		self.num = map(int, list(str(num)))

	def addends(self):
		len_num = len(self.num)
		copy = list(self.num)
		self._modify(copy, 0)
		return copy

	def checksum(self):
		return sum(self.addends())%2

	def is_valid(self):
		return self.checksum() == 0

	def create(self, number):
		copy = map(int, list(str(number)))
		self._modify(copy, 1)
		val = 10-(sum(copy)%10)
		copy2 = map(int, list(str(number)))

		if val == 10:
			copy2.append(0)
		else:
			copy2.append(val)

		return copy2

	def _modify(self, list_values, create = 0):
		len_num = len(list_values)
		for i in range(len_num):
			if i%2==(len_num + create)%2:
				total = list_values[i]*2
				if total>10:
					list_values[i] = total-9
				else:
					list_values[i] = total

#l = Luhn('123')
#print l.addends()
#print l.num
print Luhn.create(123)
#print l.checksum()
#print l.is_valid()
