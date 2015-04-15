class Bob

	def hey(arg)
		if !!(arg =~ /[A-Z][AEIOUY]/)
			return "Woah, chill out!"
		elsif !!(arg=~/.+\?\z/)
			return "Sure."
		elsif arg.strip.empty?
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
	end

end
