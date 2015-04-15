class Bob
	def hey(words)
		if said_nothing? words
			'Fine. Be that way!'
		elsif being_yelled_at? words
			'Woah, chill out!'
		elsif asked_a_question? words
			'Sure.'
		else
			'Whatever.'
		end
	end

	private
	def being_yelled_at?(words)
		(used_alphabet? words) && (screaming? words)
	end

	def screaming?(words)
		words == words.upcase
	end

	def asked_a_question?(words)
		words.end_with? '?'
	end

	def said_nothing?(words)
		words.gsub(/\s/, '').empty?
	end

	def used_alphabet?(words)
		!(words =~ /[a-zA-Z]/).nil?		
	end

end
