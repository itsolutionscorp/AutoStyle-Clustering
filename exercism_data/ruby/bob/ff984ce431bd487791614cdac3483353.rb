class Bob

	@@whatever = 'Whatever.'
	@@chillOut = 'Woah, chill out!'
	@@sure = 'Sure.'
	@@beThatWay = 'Fine. Be that way.'
	
	def hey 
		@@beThatWay
	end

	def hey(dialogue)
		return @@beThatWay if dialogue == '' || dialogue == nil
		return @@chillOut if dialogue.upcase == dialogue
		return @@sure if dialogue.rstrip.end_with?('?')
		return @@whatever
	end
end
