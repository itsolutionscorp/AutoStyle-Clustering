class Bob
	def hey(argument)
		if argument == argument.upcase && argument.upcase != argument.downcase
			'Woah, chill out!'
		elsif argument.end_with?('?')
			'Sure.'
		elsif argument.strip == ''
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end
end
