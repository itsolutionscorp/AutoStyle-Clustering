class Bob
	def hey argument
		 
		if argument.strip.empty?
			"Fine. Be that way!" 
		elsif argument == argument.upcase
			"Woah, chill out!" 
		elsif argument.end_with? "?"
			"Sure."
		else
			"Whatever."
		end

	end
end
