class Series:
	def __init__(self, str):
		self.str = str
	def slices(self, s_len):
		if s_len > len(self.str) or s_len == 0:
			raise ValueError("Invalid slice length for this series: {0}".format(s_len))
		out = []
		for i in range(0, len(self.str) - s_len + 1):
			substr = self.str[i : i+s_len]
			out.append(list(map(int, list(substr))))
		return out
