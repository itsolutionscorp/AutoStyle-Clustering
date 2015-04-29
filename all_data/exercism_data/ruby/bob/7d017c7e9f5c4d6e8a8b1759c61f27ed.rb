class Bob
# 	Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.
	def hey phrase
		if is_question?(phrase)
			'Sure.'
		elsif is_yelling?(phrase)
			'Whoa, chill out!'
		elsif is_empty?(phrase)
			'Fine. Be that way!'
		else 
			'Whatever.'
		end
	end

	def is_question?(phrase)
		!is_empty?(phrase) && phrase[-1] == '?' && !is_yelling?(phrase)
	end

	def is_yelling?(phrase)
		!is_empty?(phrase) && phrase.upcase == phrase && !is_empty?(only_words(phrase))
	end

	def is_empty?(phrase)
		phrase.nil? || phrase.strip.nil? || phrase.strip.empty?
	end

	def only_words(phrase)
		 phrase.gsub(/(\W|\d)/, '')
	end
end
