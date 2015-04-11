class Bob
	def initialize
	end
	
	def hey(words)
		case words
		when nil, "", /\s{2,}/
			'Fine. Be that way!'
		when words.upcase
			'Woah, chill out!'
		when /[a-zA-Z]+\?/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
