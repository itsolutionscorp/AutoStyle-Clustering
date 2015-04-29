class Bob

	def hey greeting
		if greeting.strip.empty?
			"Fine. Be that way!"
		elsif greeting =~ /[A-Z]/x && !(greeting =~ /[a-z]/x)
			"Woah, chill out!"
		elsif greeting =~ /\?\z/
			"Sure."
		else
			"Whatever."
		end
	end

end
