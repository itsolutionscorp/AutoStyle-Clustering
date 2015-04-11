class Bob
	def hey(x)
		case x
		when /^[^a-z]+$/
			'Woah, chill out!'
		when /\?$/
			'Sure.'
		when ''
			'Fine. Be that way!'
		when nil
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end
end
