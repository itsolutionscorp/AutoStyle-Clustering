class Bob:
	def Bob():
		return self

	def check(self,text):
		uppers = 0
		symbols = 0
		numeric = 0
		if text == '':
			return False
		
		for i in text:
			if i.isnumeric():
				numeric += 1
				continue
			elif not i.isalpha():
				symbols += 1
				continue
			elif i.isupper():
				uppers += 1
				
		return uppers == len(text)-numeric-symbols and uppers != 0

	def hey(self,text):
		text = ''.join(str(text).split(' '))
		if self.check(text):
			return 'Woah, chill out!'
		elif text[-1:] == '?':
			return 'Sure.'
		elif text == "":
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
