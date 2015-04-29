class Bob

	def hey(sentance)
		if sentance.strip.empty?
			return "Fine. Be that way!"
		elsif sentance == sentance.upcase && sentance.match(/[A-Z]/)
			return "Woah, chill out!"
		elsif sentance.end_with? "?"
			return "Sure."
		else
			return "Whatever."
		end
	end
end
