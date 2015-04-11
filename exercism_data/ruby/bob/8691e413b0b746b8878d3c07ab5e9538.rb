class Bob
	def setup
	end

	def hey(message)
		if message == '' or message =~ /^ +$/ 
			return "Fine. Be that way!"
		elsif message.upcase! == nil
			return  "Woah, chill out!"
		elsif message[-1] == "?"
			return "Sure."
		else
			return "Whatever."
		end
	end

end
