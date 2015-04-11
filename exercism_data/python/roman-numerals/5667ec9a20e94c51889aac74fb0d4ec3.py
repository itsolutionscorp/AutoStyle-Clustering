class RomanNumeral:
	def cvt(self, dig, pos):
		FIVES = ['V', 'L', 'D']
		TENS  = ['I', 'X', 'C', 'M']

		if dig == 0:
			return ""
		if dig <= 3:
			return TENS[pos]*dig
		if dig == 4:
			return TENS[pos]+FIVES[pos]
		if dig == 5:
			return FIVES[pos]
		if dig <= 8:
			return FIVES[pos]+TENS[pos]*(dig-5)
		if dig == 9:
			return TENS[pos]+TENS[pos+1]

	def __init__(self, n):
		nstr = "".join(reversed([l for l in str(n)]))
		self.r = "".join([self.cvt(int(nstr[i]), i) for i in range(len(nstr)-1, -1, -1)])

	def __str__(self):
		return self.r
