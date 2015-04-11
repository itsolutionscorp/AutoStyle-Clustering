class Bob
	
	def hey(message)
		@message = message
		if (is_Silence)
			"Fine. Be that way!"
		elsif (is_Yell)
			"Woah, chill out!"
		elsif (is_Question)
			"Sure."
		else
			"Whatever."
		end
	end
	
	def is_Silence()
		@message.strip.empty?
	end
	def is_Yell()
		@message.upcase == @message
	end
	def is_Question()
		@message[-1] == "?"
	end
end
