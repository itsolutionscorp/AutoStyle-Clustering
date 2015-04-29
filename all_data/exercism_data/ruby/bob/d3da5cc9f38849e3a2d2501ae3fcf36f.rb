class Bob
	def hey input
		if input.strip.empty?
			"Fine. Be that way!"
		elsif input.upcase === input and input =~ /.*[A-Z].*$/
			"Woah, chill out!"
		elsif input.end_with?("?")
			"Sure."
		else
			"Whatever."
		end
	end
end
