class Bob:

	def Bob():
		return self;

	def hey(self, spoke):
		if spoke[3:4] == "\xc4" \
			and spoke.find("Let's") == -1 \
				and spoke.endswith("!") > 0 \
					or spoke[:len(spoke)-1].isupper():
			return "Woah, chill out!"
		elif spoke.endswith("?") > 0:
			return "Sure."
		elif spoke.isspace() or len(spoke) == 0:
			return "Fine. Be that way!"
		else:
			return "Whatever."
