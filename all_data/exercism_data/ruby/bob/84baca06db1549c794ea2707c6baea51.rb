class Bob

	WHATEVER = 'Whatever.'
	CHILLOUT = 'Woah, chill out!'
	SURE = 'Sure.'
	BETHATWAY = 'Fine. Be that way.'

	def hey(dialogue = '')
		return BETHATWAY if dialogue == nil || dialogue.strip == ''
		return CHILLOUT if dialogue.upcase == dialogue
		return SURE if dialogue.rstrip.end_with?('?')
		return WHATEVER
	end
end
