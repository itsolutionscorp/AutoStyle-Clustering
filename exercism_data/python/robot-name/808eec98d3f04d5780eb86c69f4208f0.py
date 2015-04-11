import random 

class Robot(object):
	
	suffix = '0123456789'
	prefix = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
	
	def __init__(self):
		self._name = None
	
	def createroboname(self):
		if not self._name:
			self._name = self.part1() + self.part2()
		return self._name
	
	def eraseroboname(self):
		self._name = None 

	def part1(self):
		return ''.join([random.choice(self.prefix) for _ in range(0, 2)])

	def part2(self):
		return ''.join([random.choice(self.suffix) for _ in range(0,3)])
	
	name = property(createroboname, None, eraseroboname)

	def reset(self):
		del self.name 
