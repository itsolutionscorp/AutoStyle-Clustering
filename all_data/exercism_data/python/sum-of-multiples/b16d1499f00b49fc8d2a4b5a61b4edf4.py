class SumOfMultiples:

	def __init__(self, *number_list):
		if not number_list:
			number_list = number_list + (3,5)
		self.number_list = number_list

	def to(self, end_number):
		answer = 0
		for i in range(end_number):
			for p in self.number_list:
				if i % p == 0:
					answer+=i
					break
		return answer
