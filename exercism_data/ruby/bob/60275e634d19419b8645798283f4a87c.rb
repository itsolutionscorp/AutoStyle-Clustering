class Bob
	def hey(text)
		@text = text
		tell_reply 		= "Whatever."
		yell_reply 		= "Woah, chill out!"
		ask_reply			= "Sure."
		silence_reply	= "Fine. Be that way."

		# What bothers me is that silence? needs to come before yelling?
		# but this isn't clear from just scanning the code quickly
		if silence?
			silence_reply
		elsif yelling?
			yell_reply
		elsif asking?
			ask_reply  		
		else
		  tell_reply
		end

	end

	private

	def silence?
		@text == '' || @text == nil
	end

	def yelling?
		@text == @text.upcase
	end

	def asking?
		@text[-1] == '?'
	end

end
