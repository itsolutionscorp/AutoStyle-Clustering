

class SumOfMultiples:
	def __init__(self, num1=3, num2=5, num3=1):
		if num3 != 1:
			self.numbers = [num1, num2, num3]
		else:
			self.numbers = [num1, num2]
	
	def to(self, max):
		answer = 0
		for i in range(max):
			if any(i % num == 0 for num in self.numbers):
				answer += i
		return answer
