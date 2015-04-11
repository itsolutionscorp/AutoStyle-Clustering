class Allergies:
	def __init__(self, num):
		self.list = []
		self.possible_allergies = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]
		self.bin = "{0:b}".format(num % 256)
		for i in range(len(self.bin)):
			if self.bin[len(self.bin)-(i+1)] == "1":
				self.list.append(self.possible_allergies[i])
	def is_allergic_to(self, test):
		if test in self.list:
			return True
		return False
