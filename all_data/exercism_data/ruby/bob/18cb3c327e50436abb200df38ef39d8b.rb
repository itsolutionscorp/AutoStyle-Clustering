class Bob
	def hey(message)
		stripped_message = message.strip
		if stripped_message.empty?
			'Fine. Be that way!'
		elsif stripped_message.eql?(message.upcase)
			'Woah, chill out!'
		elsif stripped_message.end_with?('?')
			'Sure.'
		else
			'Whatever.'
		end
	end
end
