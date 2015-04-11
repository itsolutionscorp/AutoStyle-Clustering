class Phone(object):
	"""docstring for Phone"""
	def __init__(self, number):
		#super(Phone, self).__init__()
		self.origin_number = number
		self.number = self.format_number()

	def format_number(self):
		result =''
		for i in self.origin_number:
			if i.isdigit():
				result=result+str(i)
		if len(result)<10:
			print'bad number'
			return '0000000000'
		if len(result)>10 and result[0]!='1':
			print'bad number'
			return '0000000000'
		if len(result)>10 and result[0]=='1':
			result=result[1:]
			#print 'here', result
			return result
		else:
			return result

	def area_code(self):
		return self.number[0:3]

	def pretty(self):
		result='('+self.number[0:3]+')'+' '+self.number[3:6]+'-'+self.number[6:]
		return result

	
