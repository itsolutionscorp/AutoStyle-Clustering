class Bob

	def hey(greeting)
		response = "Whatever."
		
		if (greeting == greeting.upcase && greeting =~ /[A-Z]/)
			response = 'Whoa, chill out!'
		elsif (greeting[-1] == '?')
			response = 'Sure.'
		elsif (greeting =~ /\A\s*\z/)
			response = 'Fine. Be that way!'
		end

		return response
	end

end
