class Bob
	def hey(input)
		if(input =~ /[[:alpha:]]/ && input == input.upcase)
			return 'Woah, chill out!'
		elsif(input[-1] == '?')
			return 'Sure.'
		elsif(input =~ /^\s*$/ && input !~ /[[:alpha:]]/)
			return 'Fine. Be that way!'
		end

		"Whatever."
	end
end
