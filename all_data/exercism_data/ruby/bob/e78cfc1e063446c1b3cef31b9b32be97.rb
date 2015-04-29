class Bob

	attr_reader :teenager

	def initialize
		@teenager
	end

	def hey phrase
		case
		when phrase == phrase.upcase && !only_numbers?(phrase)
			"Woah, chill out!"
		when phrase.end_with?('?')
			"Sure."
		when phrase.strip.length == 0
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	def only_numbers? str
		str.gsub(/(\W|\d|\s)/, "").length == 0
	end

end
