class Bob

	def hey(something)
		if something == something.upcase && something =~ /[A-Z]/
			"Woah, chill out!"
		elsif something.end_with?("?")
			"Sure."
		elsif something.strip.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

end
