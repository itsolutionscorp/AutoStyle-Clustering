class Bob
	def hey(s)
		if s =~ /[A-Z]+/ and not s =~ /[a-z]/
			"Woah, chill out!"
		elsif s[-1] == '?'
			"Sure."
		elsif s =~ /\A\s*\z/
			"Fine. Be that way!"
		else	
			"Whatever."
		end
	end
end
