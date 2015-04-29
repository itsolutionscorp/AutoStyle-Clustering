class Bob

	def hey(query)
		if blank?(query)
			response = "Fine. Be that way!"
		elsif is_allcaps?(query) && !contains_only_numbers?(query)
			response = "Woah, chill out!"
		elsif query.end_with?("?")
			response = "Sure."
		else
			response = "Whatever."
		end

		return response
	end

	def is_allcaps?(query)
		if query == query.upcase #|| query.end_with?("!")
			return true
		else
			return false
		end
	end

	def contains_only_numbers?(query)
		if query =~ /\A(\d|\s|,|\?)*\z/
			return true
		else
			return false
		end
	end

	def blank?(query)
		if query =~ /\A\s*\z/
			return true
		else
			return false
		end
	end
end
