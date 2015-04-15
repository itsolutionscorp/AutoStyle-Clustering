class Robot():

	def reset(self):
		import string
		import random
		lets = [string.ascii_uppercase]*2
		nums = [string.digits]*3
		rc = random.choice
		name2 =  ''.join(map(rc,lets)+map(rc,nums))
		if name2 == self.name:
			reset()
		else:
			self.name = name2


	def __init__(self):
		import string
		import random
		lets = [string.ascii_uppercase]*2
		nums = [string.digits]*3
		rc = random.choice
		self.name = ''.join(map(rc,lets)+map(rc,nums))
