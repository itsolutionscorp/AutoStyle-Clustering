

class SumOfMultiples:
	def __init__(self, *nums):
		self.numbers = nums or [3, 5]
	
	def to(self, max):
		answer = 0
		for i in range(max):
			if any(i % num == 0 for num in self.numbers):
				answer += i
		return answer
