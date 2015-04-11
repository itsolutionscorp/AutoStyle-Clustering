class Bob
	def hey sentence
		return 'Fine. Be that way!' if empty?(sentence)
		return 'Woah, chill out!' if yelling?(sentence)
		return 'Sure.' if question?(sentence)
		return 'Whatever.'
	end	

private
	def yelling? sentence
	 	sentence.upcase == sentence		
	end
	
	def question? sentence
		sentence[-1] == '?'
	end 

	def empty? sentence
		sentence.nil? || sentence.strip.empty?
	end	
end	
