class Bob

	def hey str
		return 'Fine. Be that way!' unless str =~ /\w/
		return 'Whoa, chill out!' if str.length / 4 < str.scan(/[A-Z!]/).size 
		return 'Sure.' if str[-1] == '?'
		'Whatever.'
	end

end
