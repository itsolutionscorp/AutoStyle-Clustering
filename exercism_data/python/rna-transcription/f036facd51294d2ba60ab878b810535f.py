class DNA:
	def __init__(self,inputString):
		self.inputString = inputString;
				
	def to_rna(self):
		resultString = "";
		for character in self.inputString:
			if character == "G":
				resultString += "C"
			if character == "C":
				resultString += "G"
			if character == "T":
				resultString += "A"
			if character == "A":
				resultString += "U"
		return resultString
		
