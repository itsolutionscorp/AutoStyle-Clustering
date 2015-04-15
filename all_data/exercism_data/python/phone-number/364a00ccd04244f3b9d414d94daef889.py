def numCheck(inp):
	num = []
	for i in inp:
		try:
			num.append(str(int(i)))
		except ValueError:
			continue
	number = ''.join(num)
	if len(number) == 10 or (len(number)==11 and number[0]=='1'):
		pNum=number[-10:]
		return pNum
	else:
		pNum= '0'*10
		return pNum

class Phone:
	def __init__(self,inp):
		self.number = numCheck(inp)
	def area_code(self):
		return self.number[:3]
	def pretty(self):
		return "("+self.number[:3]+") "+self.number[3:6] + "-"+self.number[6:]
