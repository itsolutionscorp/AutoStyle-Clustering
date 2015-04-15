class Bob
	def hey(message)
		if /[?]$/.match(message)
			return 'Sure.'
		end

		if !/[a-z]/.match(message)
			return 'Woah, chill out!'
		end

		if message == ''
			return 'Fine. Be that way!'
		end

		return 'Whatever.'
	end
end
