class Bob
	def hey(message)
		if isShouting(message)
			"Woah, chill out!"
		elsif isQuestion(message)
			"Sure."
		elsif isMeaningless(message)
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end
	
	def isShouting(message)
		return (
			!isEmpty(message) &&
			containsAnUpperCaseLetter(message) &&
			!containsALowerCaseLetter(message)
		)
	end
	
	def containsAnUpperCaseLetter(message)
		return (message.index(/[A-Z]/) != nil)
	end
	
	def containsALowerCaseLetter(message)
		return (message.index(/[a-z]/) != nil)
	end
	
	def isQuestion(message)
		return endsWith(message, "?")
	end
	
	def endsWith(message, char)
		lastChar = message[-1,1]
		return (lastChar == char)
	end
	
	def isMeaningless(message)
		return isEmpty(message)
	end
	
	def isEmpty(message)
		return message.strip.empty?
	end
end
