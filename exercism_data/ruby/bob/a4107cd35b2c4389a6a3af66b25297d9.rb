class Bob

	WHATEVER = 'Whatever.'
	CHILLOUT = 'Woah, chill out!'
	SURE = 'Sure.'
	BE_THAT_WAY = 'Fine. Be that way.'

	private

	def saidAnything? (dialogue)
		not (dialogue == nil || dialogue.strip == '')
	end

	def yelling? (dialogue)
		dialogue.upcase == dialogue
	end

	def questioning? (dialogue)
		dialogue.rstrip.end_with?('?')
	end

	public

	def hey(dialogue = '')
		return BE_THAT_WAY if not saidAnything?(dialogue)
		return CHILLOUT if yelling?(dialogue)
		return SURE if questioning?(dialogue)
		return WHATEVER
	end
end
