class Bob

	def hey(say)
		say = say.to_s
		case say
		when /^\s*$/
			'Fine. Be that way!'
		when say.upcase
			'Woah, chill out!'
		when /\?$/
			'Sure.'
		else
			'Whatever.'
		end
	end

end
