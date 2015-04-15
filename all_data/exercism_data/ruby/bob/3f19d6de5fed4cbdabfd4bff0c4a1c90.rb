class Bob
	def hey(str)
	return 'Fine. Be that way!' if nil == str || str.strip.empty?
	return 'Woah, chill out!' if str == str.upcase  
	return 'Sure.' if str[-1] == '?'
	'Whatever.'
	end
end
