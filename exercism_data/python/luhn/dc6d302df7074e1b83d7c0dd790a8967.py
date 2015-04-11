class Luhn(object):
	
	def __init__(self, num):
		self.num = map(int, list(str(num)))

	def addends(self):
		return self.__modify(self.num[:], False)

	def checksum(self):
		return sum(self.addends())%10

	def is_valid(self):
		return self.checksum() == 0

	@staticmethod
	def create(number):
		num = map(int, list(str(number)))
		l = Luhn(number)
		val = 10-(sum(l.__modify(num[:], True))%10)

		num.append(val%10)
		s = ''.join(map(str, num))
		return int(s)

	def __modify(self, list_values, create_luhn = False):
		len_num = len(list_values)
		create = 1 if create_luhn else 0
		for i in range(len_num):
			if i%2==(len_num + create)%2:
				total = list_values[i]*2
				if total>10:
					list_values[i] = total-9
				else:
					list_values[i] = total

		return list_values
