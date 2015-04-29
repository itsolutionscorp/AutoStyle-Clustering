class Bob

	def hey phrase
		case
		when phrase == phrase.upcase && !only_numbers?(phrase)
			"Woah, chill out!"
		when question?(phrase)
			"Sure."
		when silent?(phrase)
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	def only_numbers? str
		str.gsub(/(\W|\d|\s)/, "").length == 0
	end

	def question? str
		str.to_s.end_with?('?')
	end

	def silent? str
		str.strip.length == 0
	end

end
