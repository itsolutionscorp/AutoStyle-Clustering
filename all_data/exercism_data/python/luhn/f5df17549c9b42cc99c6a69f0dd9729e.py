class Luhn(object):
	
	def __init__(self, num):
		self.num = map(int, list(str(num)))

	def addends(self):
		self._modify(self.num, 0)
		return self.num

	def checksum(self):
		return sum(self.addends())%10

	def is_valid(self):
		return self.checksum() == 0

	@staticmethod
	def create(number):
		num = map(int, list(str(number)))
		l = Luhn(number)
		l._modify(num, 1)
		val = 10-(sum(num)%10)
		copy2 = map(int, list(str(number)))

		copy2.append(val%10)
		s = ''.join(map(str, copy2))
		return int(s)

	def _modify(self, list_values, create = 0):
		len_num = len(list_values)
		for i in range(len_num):
			if i%2==(len_num + create)%2:
				total = list_values[i]*2
				if total>10:
					list_values[i] = total-9
				else:
					list_values[i] = total
