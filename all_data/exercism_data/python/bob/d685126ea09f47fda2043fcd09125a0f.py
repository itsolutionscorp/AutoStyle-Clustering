class Bob:
	def hey(self, sent):
		if (sent.isupper()) : 	    return "Woah, chill out!"
		elif (sent.strip() == '') : return "Fine. Be that way!"
		elif (sent.endswith('?')) : return "Sure."
		else: 			    return "Whatever."
	
sample = Bob()
print sample.hey('Whats up?')
