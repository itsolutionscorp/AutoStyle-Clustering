def allergyList(l):
	n = l%256
	aDict = {
	1 : "eggs",
	2 : "peanuts", 
	4 : "shellfish",
	8 : "strawberries",
	16: "tomatoes",
	32: "chocolate",
	64: "pollen",
	128: "cats"
	}
	binNum ='0'*(8-len(bin(n)[2:]))+bin(n)[2:]
	binNum = binNum[:8][::-1]
	return [aDict[2**(i)] for i in range(8) if binNum[i] == '1']

class Allergies():
	def __init__(self,n):
		self.list = allergyList(n)
	def is_allergic_to(self,x):
		return True if x in self.list else False
