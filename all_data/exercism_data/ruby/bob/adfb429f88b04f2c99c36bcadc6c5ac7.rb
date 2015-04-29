class Bob
	def hey(greeting)
		# Run through possible greetings and their respective replies
		case
		when greeting.strip == ""
			return "Fine. Be that way!"
		when greeting == greeting.upcase
			return "Woah, chill out!"
		when greeting.end_with?("?")
			return "Sure."
		else
			return "Whatever."
		end			
	end
end
