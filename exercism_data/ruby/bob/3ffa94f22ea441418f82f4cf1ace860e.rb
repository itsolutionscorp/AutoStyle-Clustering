class Bob
	def hey(words)
		if words.eql?(words.downcase)
			words.capitalize!
		end
		if (words.strip.length == 0)
			"Fine. Be that way!"
		elsif words.eql?(words.downcase) != words.eql?(words.upcase)
			"Woah, chill out!"
		elsif words.end_with?("?")
			"Sure."
		else
			"Whatever."
		end			
	end
end
