class Bob

	def hey(greeting)
		response = "Whatever."
		
		if (greeting == greeting.upcase && greeting.match(/[A-Z]/).to_s.size > 0)
			response = 'Whoa, chill out!'
		elsif (greeting[-1] == '?')
			response = 'Sure.'
		elsif (greeting.match(/[^\s]/).to_s.size == 0)
			response = 'Fine. Be that way!'
		end

		return response
	end

end
