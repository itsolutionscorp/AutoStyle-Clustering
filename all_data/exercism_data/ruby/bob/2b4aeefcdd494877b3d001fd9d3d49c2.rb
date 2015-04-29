class Bob
	def hey(msg)
		@msg = msg
		return 'Fine. Be that way!' if silence?
		return 'Woah, chill out!' if shouting?
		return 'Sure.' if asking?
		'Whatever.'
	end

	def silence?
		@msg.to_s.empty?
	end

	def shouting?
		@msg == @msg.upcase
	end

	def asking?
		@msg.end_with? '?'
	end
end
