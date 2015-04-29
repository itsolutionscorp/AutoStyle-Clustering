class Bob
	
	def hey(message)
		return 'Sure.' if message =~ /\?$/
		return 'Woah, chill out!' unless message.upcase!
		return 'Fine. Be that way!' if message.empty?
		return 'Whatever.'
	end
end
