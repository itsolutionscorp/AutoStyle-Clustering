class Bob
	def hey(text)
		tell_reply 		= "Whatever."
		yell_reply 		= "Woah, chill out!"
		ask_reply			= "Sure."
		silence_reply	= "Fine. Be that way."
		if text == '' || text == nil
			silence_reply
		elsif text == text.upcase
			yell_reply
		elsif text[-1] == '?'
			ask_reply  		
		else
		  tell_reply
		end
	end
end
