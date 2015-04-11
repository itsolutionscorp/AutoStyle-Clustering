class Bob

	def hey(msg)
		return 'Fine. Be that way!' if blank?(msg)
		return 'Woah, chill out!' if yelling?(msg)
		return 'Sure.' if question?(msg)
		'Whatever.'
	end

private
	
	def blank?(msg)
		msg.nil? || (msg.strip == '')
	end

	def yelling?(msg)
		msg.upcase == msg
	end

	def question?(msg)
		msg.chars.last == '?'
	end

end
