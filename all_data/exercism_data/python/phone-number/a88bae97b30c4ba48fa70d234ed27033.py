class Phone (object):
	def __init__(self, raw):
		self.number = ''.join(d for d in raw if d.isdigit())
		self.check()
	
	def check(self):
		if self.number[0] == '1' and len(self.number) > 10:
			self.number = self.number[1:]
		if len(self.number) != 10:
			self.number = '0000000000'

	def area_code(self):
		return self.number[:3]
	
	def pretty(self):
		return '(%s) %s-%s' % (self.number[:3],
		                       self.number[3:6],
		                       self.number[6:])
