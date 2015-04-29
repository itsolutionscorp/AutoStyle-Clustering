class Bob
	def hey sentence
		return 'Fine. Be that way!' if (sentence.nil? || sentence.strip.empty?)
		return 'Woah, chill out!' if sentence.upcase == sentence
		return 'Sure.' if sentence[-1] == '?'
		return 'Whatever.'
	end	
end	
