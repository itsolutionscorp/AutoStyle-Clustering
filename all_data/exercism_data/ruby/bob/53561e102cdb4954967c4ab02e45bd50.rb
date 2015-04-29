class Bob
	def hey word
		case
		when WhatSay.is_without_anything?(word)
			'Fine. Be that way!'
		when WhatSay.is_yelling?(word)
			"Woah, chill out!"
		when WhatSay.is_a_question?(word)
			"Sure."
		else
		  "Whatever."
		end
	end
end 

class WhatSay
	#a question usually end with ?
	def self.is_a_question? word
		word.end_with?("?")
	end

	#what you yell at him are all uppercased
	def self.is_yelling? word
		word.upcase == word
	end

	#you address him without actually anything, like "" and "  "
	def self.is_without_anything? word
		word.strip.empty?
	end
end
