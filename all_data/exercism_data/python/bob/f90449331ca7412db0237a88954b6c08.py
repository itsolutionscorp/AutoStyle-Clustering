class bob:
	def hey(self, speak):
		if speak.str.isupper():
			return "Whoa, chill out!"
		elif not speak:
			return "Fine be that way."
		elif speak.endswith("?"):
				return "Sure."
		return "Whatever"
	
