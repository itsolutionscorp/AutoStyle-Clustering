class Bob
	def hey message
		if message.strip == ''
			return "Fine. Be that way!" 
		end
		if message[/[A-Z]/] and not message[/[a-z]/]
			return "Woah, chill out!"
		end
		if message[-1] == '?'
			return "Sure." 
		end
		return "Whatever."
	end
end
