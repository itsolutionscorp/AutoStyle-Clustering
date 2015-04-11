class Bob

	def hey(words)
		case
		when nothing?(words)
			'Fine. Be that way!'
		when yelling?(words)
			'Woah, chill out!'
		when question?(words)
			'Sure.'
		else
			'Whatever.'
		end
	end

	def yelling?(words)
		words == words.upcase
	end

	def question?(words)
		words.end_with?('?')
	end

	def nothing?(words)
		words.strip.empty?
	end

end
