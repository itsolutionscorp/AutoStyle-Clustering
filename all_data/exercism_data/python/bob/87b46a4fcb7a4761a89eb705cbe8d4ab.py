class Bob:
	def isAscii(text):
		try:
			text.decode('ascii')
		except UnicodeDecodeError:
			return False
		return True
 
	def hey(__self__, text):
		text=text.strip();
		if len(text)==0:
			return 'Fine. Be that way!'
		elif text.isupper():
			return 'Woah, chill out!'
		elif text[-1]=="?":
			return 'Sure.'
		elif text.isupper():
			return 'Woah, chill out!'
		else: return 'Whatever.'
