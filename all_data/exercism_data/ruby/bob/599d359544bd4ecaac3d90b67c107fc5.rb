class Bob
	def hey(word)
		@word = word
		#a question usually end with ?
		def ask_a_question
			return @word.end_with?("?") ? true : false
		end

		#what you yell at him are all uppercased
		def yell
			return  @word.match(/[a-z]/).nil?? true : false
		end

		#you address him without actually anything, like "" and "  "
		def address_him_without_anything
			return @word.strip.empty?? true : false
		end

		case
		when address_him_without_anything
			'Fine. Be that way!'
		when yell
			"Woah, chill out!"
		when ask_a_question
			"Sure."
		else
		  "Whatever."
		end
	end
end
