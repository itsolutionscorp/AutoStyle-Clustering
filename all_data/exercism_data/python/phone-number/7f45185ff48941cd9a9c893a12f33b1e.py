class Phone:
	bad = "0000000000"

	def __init__(self, text):
		self.number = Phone.parse(text)

	@staticmethod
	def parse(text):
		text = "".join(char for char in text if char in "0123456789")

		if len(text) == 10:
			return text
		elif len(text) == 11 and text.startswith('1'):
			return text[1:]
		return Phone.bad

	def area_code(self):
		return self.number[:3]

	def pretty(self):
		return "({0}) {1}-{2}".format(self.number[:3], self.number[3:6], self.number[6:])
