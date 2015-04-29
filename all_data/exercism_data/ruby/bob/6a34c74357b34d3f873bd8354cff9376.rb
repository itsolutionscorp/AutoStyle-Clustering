class Bob
	def hey(phrase)
		return "Fine. Be that way!" if phrase.strip.empty?

		# Return Sure if the phrase ends with a ?
		return "Sure." if question?(phrase) && yelling?(phrase) == false

		# Return Woah, chill out! if the whole phrase is already uppercase (in which case upcase! returns nil)
		return "Woah, chill out!" if yelling?(phrase)
			
		# Return the default answer otherwise
		return "Whatever."
	end

	private
	def yelling?(phrase)
		return true if contains_letters?(phrase) && phrase.upcase! == nil
		
		return false;
		
	end

	def question?(phrase)
		phrase.end_with?('?')
	end

	def contains_letters?(phrase)
		phrase.scan(/[a-zA-Z]/).empty? == false
	end
end
