class Bob

	def hey (message)
		if message.upcase == message && message.strip.empty? == false && message[/[A-Za-z]/].to_s.empty? == false
			return "Woah, chill out!"
		elsif message.end_with? ("?")
			return "Sure."
		elsif message.strip.empty?
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
				
	end

end
