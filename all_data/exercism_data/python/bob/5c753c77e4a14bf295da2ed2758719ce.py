class Bob:
	def hey(self, s):
		if not s or s.strip() == "":
			return "Fine. Be that way!"
		if s == s.upper():
			return "Woah, chill out!"
		if s[-1] == '?':
			return "Sure."
		return "Whatever."
