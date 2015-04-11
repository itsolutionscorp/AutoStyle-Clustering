"""
* eggs (1)
* peanuts (2)
* shellfish (4)
* strawberries (8)
* tomatoes (16)
* chocolate (32)
* pollen (64)
* cats (128)
"""


class Allergies:
	def __init__(self,val):
		p = (str(bin(val))[2::])[::-1]
		inputlist = ["eggs",
					"peanuts",
					"shellfish",
					"strawberries",
					"tomatoes",
					"chocolate",
					"pollen",
					"cats"] 
		l = []
		for x,y in zip(p,inputlist):
			if x == '1':
				l.append(y)
		self.list = l

	def is_allergic_to(self,instr):
		return (instr in self.list)


print Allergies(1).is_allergic_to("eggs")
