class bob:
	# Class variables
	@classmethod
	def hey(self,text):
		has_letters = any(c.isalpha() for c in text)
		
		is_caps = text.upper() == text and has_letters
		
		is_empty = not text.strip()

		ends_in_explanation_mark = not is_empty and text[-1] == "!"

		if(not text.strip()):
			return "Fine. Be that way!"
		elif(is_caps):
			return "Whoa, chill out!"
		elif(text[-1] == "?"):
			return "Sure."
		else:
			return "Whatever."
