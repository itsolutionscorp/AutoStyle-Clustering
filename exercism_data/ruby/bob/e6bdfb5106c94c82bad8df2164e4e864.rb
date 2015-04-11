class Bob

	def hey(string)
		case 
		when string_is_empty?(string) 								then "Fine. Be that way!"
		when string_has_all_caps?(string)							then "Woah, chill out!"
		when string_ends_in_a_question_mark?(string) 	then "Sure."
		else 																							 
			"Whatever."
		end
	end

	private

	def string_is_empty?(string)
		string.strip.empty?
	end

	def string_has_all_caps?(string)
		string == string.upcase
	end

	def string_ends_in_a_question_mark?(string)
		string.end_with?("?")
	end
end
