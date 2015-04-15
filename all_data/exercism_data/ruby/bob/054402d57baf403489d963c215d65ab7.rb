class Bob
	def hey(text)
		return 'Fine. Be that way!' if text.nil? || text.strip.empty?
		return 'Woah, chill out!' if text == text.upcase
		return 'Sure.' if text.end_with? '?'
		return 'Whatever.'
	end
end
