class Bob
	
	def hey(message)
		@message = message
		if (isSilence)
			"Fine. Be that way!"
		elsif (isYell)
			"Woah, chill out!"
		elsif (isQuestion)
			"Sure."
		else
			"Whatever."
		end
	end
	
	def isSilence()
		@message.strip.empty?
	end
	def isYell()
		@message.upcase == @message
	end
	def isQuestion()
		@message[-1] == "?"
	end
end
