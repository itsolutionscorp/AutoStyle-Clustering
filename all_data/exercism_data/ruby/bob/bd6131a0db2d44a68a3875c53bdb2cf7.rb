class Bob

	WHATEVER = 'Whatever.'
	CHILLOUT = 'Woah, chill out!'
	SURE = 'Sure.'
	BE_THAT_WAY = 'Fine. Be that way.'

	private

	def said_anything? (dialogue)
		not (dialogue == nil or dialogue.strip.empty?)
	end

	def yelling? (dialogue)
		dialogue.upcase == dialogue
	end

	def questioning? (dialogue)
		dialogue.rstrip.end_with?('?')
	end

	public

	def hey(dialogue = '')
		return BE_THAT_WAY if not said_anything?(dialogue)
		return CHILLOUT if yelling?(dialogue)
		return SURE if questioning?(dialogue)
		WHATEVER
	end
end
