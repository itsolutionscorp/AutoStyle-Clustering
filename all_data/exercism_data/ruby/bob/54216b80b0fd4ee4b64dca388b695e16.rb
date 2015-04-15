class Bob
	def hey(phrase)
		return 'Whoa, chill out!' if yell?(phrase)
		return 'Sure.' if question?(phrase)
		return 'Fine. Be that way!' if silence?(phrase)
		return 'Whatever.'
	end
private
	def yell?(phrase)
		return phrase == phrase.upcase if phrase.match /[a-zA-Z]/
		return false
	end
	
	def question?(phrase)
		phrase[-1] == '?'
	end
	
	def silence?(phrase)
		phrase.strip.empty?
	end
end
