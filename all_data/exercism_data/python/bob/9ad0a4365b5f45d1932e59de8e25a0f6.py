class Bob:
	def hey(self,message):
		if message is None:
			return  'Fine. Be that way!'
		if (len(message.strip()) == 0):
			return 'Fine. Be that way!'
		#since its passed as string, we need to decode it with unicode escape 
		message = message.decode('unicode-escape')
		if message.isupper():
			return "Woah, chill out!"
		# looking only at the last character , to see if its a question.
		if message[len(message)-1:]=='?':
			return 'Sure.'
		return "Whatever."
"""	
if __name__=='__main__':
	bob = Bob()
	print bob.hey(u"\xdcML\xc4\xdcTS!")

"""
