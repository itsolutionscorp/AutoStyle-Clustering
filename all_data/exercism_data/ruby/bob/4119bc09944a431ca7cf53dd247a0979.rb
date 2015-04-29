class Bob
	def hey(phrase)
		# The empty case
		if phrase.strip.empty?
			"Fine. Be that way!"
		elsif (question?(phrase) && yelling?(phrase) == false)
			"Sure."
		elsif yelling?(phrase)
			"Woah, chill out!"
		else
			"Whatever."
		end
	end

	private
	def yelling?(phrase)
		(contains_letters?(phrase) && phrase.upcase! == nil)
	end

	def question?(phrase)
		phrase.end_with?('?')
	end

	def contains_letters?(phrase)
		!phrase.scan(/[a-zA-Z]/).empty?
	end
end
