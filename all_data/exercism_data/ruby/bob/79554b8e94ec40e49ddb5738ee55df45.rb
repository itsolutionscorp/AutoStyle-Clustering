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
		(!only_numbers? words) && (all_caps words)
	end

	def all_caps(words)
		words == words.upcase
	end

	def asked_a_question?(words)
		words.end_with? '?'
	end

	def said_nothing?(words)
		words.gsub(/\s/, '').empty?
	end

	def without_punctuation(words)
		words.gsub(/(,|!|\?|\.|\s)/, '')
	end

	def only_numbers?(words)
		no_punc = without_punctuation words
		!(no_punc =~ /^[0-9]+$/).nil?
	end
end
