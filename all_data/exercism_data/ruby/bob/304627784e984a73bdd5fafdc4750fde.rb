class Bob
	def hey(message)
		message.strip!
		return 'Fine. Be that way!' if message.empty?
		return 'Whoa, chill out!' if message =~ /[A-Z]/ && not(message =~ /[a-z]/)
		return 'Sure.' if message.end_with?('?')
		'Whatever.'
	end
end
