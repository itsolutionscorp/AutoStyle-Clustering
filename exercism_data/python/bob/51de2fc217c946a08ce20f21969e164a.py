def hey(inTextRaw):
	inText = str(inTextRaw)

	
	def isSilence(inText):
		return inText.strip() == ""
	
	def isYell(inText):
		return inText.isupper()
	
	def isQuestion(inText):
		return inText[-1] == "?"
		
			
	if isSilence(inText):
		return "Fine. Be that way!"
	if isYell(inText):
		return "Whoa, chill out!"
	if isQuestion(inText):
		return "Sure."
	else:
		return "Whatever."
