
class Luhn(object):

	def __init__(self, number):
		self.number = number

	def doubler(self, values):
		doubled = list(str(values))
		doubled = map(int, doubled)
		count = 1
		for i in range(len(doubled)):
			if count == 0:
				x = doubled[-i - 1] * 2
				if x > 9:
					x -= 9
				doubled[-i - 1] = x
				count = 1
			else:
				count = 0
		return doubled
	
	def addends(self):
		return self.doubler(self.number)
		

	def checksum(self):
		cs = sum(self.addends())
		cs = str(cs)
		cs = int(cs[-1])
		return cs
		
	def is_valid(self):
		if self.checksum() == 0:
			return True
		else:
			return False
	
	def create(value):
		v = Luhn(value*10)
		if v.is_valid() == False:
			lnumb = map(int, (list(str(value*10))))
			for i in range(0, 10):
				lnumb[-1] = i
				jnumb = map(str, lnumb)
				jnumb = ''.join(jnumb)
				junmb = int(jnumb)
				newdouble = v.doubler(jnumb)
				if str(sum(newdouble))[-1] == '0':
					return jnumb
		else:
			return True
