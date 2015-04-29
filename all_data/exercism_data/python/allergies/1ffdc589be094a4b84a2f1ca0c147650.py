class Allergies:
	def __init__(self,result):
		bitstring="{0:0>8b}".format(result);
		length=len(bitstring)
		if(length>8):
			bitstring=bitstring[length-8:length]
		print bitstring
		self.list=[]
		if(bitstring[7]=='1'):
			self.list.append('eggs')
		if(bitstring[6]=='1'):
			self.list.append('peanuts')
		if(bitstring[5]=='1'):
			self.list.append('shellfish')
		if(bitstring[4]=='1'):
			self.list.append('strawberries')
		if(bitstring[3]=='1'):
			self.list.append('tomatoes')
		if(bitstring[2]=='1'):
			self.list.append('chocolate')
		if(bitstring[1]=='1'):
			self.list.append('pollen')
		if(bitstring[0]=='1'):
			self.list.append('cats')
	def is_allergic_to(self, test):
		if (test in self.list):
			return True
		else:
			return False
