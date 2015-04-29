import unicodedata

class Bob:
	messages = {
		"default" : "Whatever.",
		"yell" : "Woah, chill out!",
		"question" : "Sure.",
		"empty" : "Fine. Be that way!"
	}
	
	def hey(self, message):
		#Remove whitespace for less processing
		message = message.strip()
		
		lowercaseCount = 0
		uppercaseCount = 0
		numberCount = 0
		
		#Convert to unicode and check each character to see what category it is
		for character in unicode(message):
			characterCategory = unicodedata.category(character)
			
			if characterCategory == "Ll": #Lowercase
				lowercaseCount += 1
			elif characterCategory == "Lu": #Uppercase
				uppercaseCount += 1
			elif characterCategory == "Nd": #Number
				numberCount += 1
		
		#If message has nothing, or only punctuation, it's "empty"
		if lowercaseCount == 0 and uppercaseCount == 0 and numberCount == 0:
			return self.messages["empty"]
		
		#If message has no lowercase but has uppercase, it's yelling
		if lowercaseCount == 0 and uppercaseCount > 0:
			return self.messages["yell"]
		
		#Get punctuation from end of message
		punctuation = message[-1]
		
		#If it's a question mark, the message is a question
		if punctuation == '?':
			return self.messages["question"]
		
		#No message type found, return default response
		return self.messages["default"]
