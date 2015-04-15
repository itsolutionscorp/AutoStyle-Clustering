class Bob

	def hey(arg)
		if arg.strip == ''
			'Fine. Be that way!'
		elsif arg.upcase == arg && /[a-zA-Z]+/.match(arg)
			'Woah, chill out!'
		elsif /\?$/.match(arg) && !/\n/.match(arg)
			'Sure.'
		else
			return 'Whatever.'
		end
	end

end
