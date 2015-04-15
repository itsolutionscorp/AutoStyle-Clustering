class Bob
	def hey(word)
		case
		when address_him_without_anything(word)
			'Fine. Be that way!'
		when yell(word)
			"Woah, chill out!"
		when ask_a_question(word)
			"Sure."
		else
		  "Whatever."
		end
	end
	
	private 
	#a question usually end with ?
	def ask_a_question word
		word.end_with?("?") ? true : false
	end

	#what you yell at him are all uppercased
	def yell word
		word.match(/[a-z]/).nil?? true : false
	end

	#you address him without actually anything, like "" and "  "
	def address_him_without_anything word
		word.strip.empty?? true : false
	end
end
