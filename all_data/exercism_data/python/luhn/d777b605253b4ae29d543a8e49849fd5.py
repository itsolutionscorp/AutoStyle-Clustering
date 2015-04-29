class Luhn:

	def __init__(self, n):
		self.n = n

	def addends(self):
		return(self.calculate_addends(self.n))

	def calculate_addends(self, n):
		nums = str(n)
		i = 1
		numbers = [None] * len(nums)
		while i <= len(nums):
			if i % 2 == 0:
				number = int(nums[len(nums) - i]) * 2
				if number >= 10:
					number -= 9
				numbers[len(nums) - i] = number
			else:

				numbers[len(nums) - i] = int(nums[len(nums) - i])
			i += 1
		
		return(numbers)


	def checksum(self):
		numbers = self.calculate_addends(self.n)
		return((sum(numbers) % 10))

	def is_valid(self):
		luhn = sum(self.addends())
		if luhn % 10 == 0:
			return(True)
		return(False)

	def create(n):
		luhn = Luhn(n * 10)
		print("create:")
		print(luhn.n)
		if luhn.checksum() == 0:
			return(luhn.n)
		return(luhn.n + (10 - luhn.checksum()))
