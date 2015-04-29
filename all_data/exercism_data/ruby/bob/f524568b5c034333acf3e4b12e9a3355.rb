class Bob
	def hey(text)
		# If nothing was said at all
		return 'Fine. Be that way!' if text.nil? || text.strip.empty?

		# If text is all caps (shouting)
		return 'Woah, chill out!' if text == text.upcase

		# If text is a question
		return 'Sure.' if text.end_with? '?'

		# Anything else
		return 'Whatever.'
	end
end
