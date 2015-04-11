class Bob
	def hey phrase
		return 'Fine. Be that way!' if silent? phrase
		return 'Woah, chill out!' if yelling? phrase
		return 'Sure.' if question? phrase
		'Whatever.'
	end

	private
	def silent? phrase
		phrase.gsub(/\s/, '') == ''
	end

	def yelling? phrase
		letters = phrase.gsub(/[^A-Za-z]/, '')
		letters.length > 0 && letters.upcase == letters
	end

	def question? phrase
		phrase.end_with? '?'
	end
end
