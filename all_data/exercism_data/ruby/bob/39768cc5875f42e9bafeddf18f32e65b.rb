class Bob

	def hey phrase
		phrase = phrase.strip

		if phrase.empty?
			return "Fine. Be that way!"
		elsif phrase.eql?(phrase.upcase)
	    	return "Woah, chill out!"
	    elsif phrase[-1] == "?"
	    	return "Sure."
	    end
		return "Whatever."
	end

end
