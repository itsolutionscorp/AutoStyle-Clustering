class Phone(object):
	def __init__(self,input):
		self.input = input
		self.make_num()
	
	def make_num(self):
		num = [i for i in self.input if i in '1234567890']
		if len(num) == 10:
			self.number = ''.join(num)
		elif len(num) == 11 and num[0] == '1':
			self.number = ''.join(num[1:])
		else:
			self.number = '0000000000'
	
	def area_code(self):
		return self.number[:3]
	
	def pretty(self):
		return "(%s) %s-%s" % (self.number[:3],self.number[3:6],self.number[6:])
