class Phone(object):
	
	def __init__(self, number):
		self.number = number

	def phone(self):
		#Invalidate  numbers larger than 11 smaller than 9 & clean out char

		plist = ''.join(i for i in self.number if i.isdigit())	
		
		if len(plist) == 11 and plist[0] == "1":
			return plist.replace(plist[0], '', 1)
		elif len(plist) >= 11:
			return '0' * 10
		elif len(plist) <= 9:
			return '0' * 10
		else:
			return plist 
		
	def pretty(self):
		#Make numbers formatted

		newnumber = list(self.number)
		
		if len(newnumber) >= 10:
			newnumber = '('+self.number[-10:-7]+') '+self.number[-7:-4]+'-'+self.number[-4:]
			return newnumber
		elif len(newnumber) == 11:
			newnumber = newnumber.strip(-11)
			newnumber = "("+self.number[-10:]+') '+self.number[-7:-4]+'-'+self.number[-4:]
			return newnumber
	
	def area_code(self):
		#Return just the area_code

		return self.number[:3]
