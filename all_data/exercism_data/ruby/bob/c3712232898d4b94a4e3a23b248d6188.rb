class Bob
	def hey word
		@what_say = WhatSay.new(word)
		case
		when @what_say.without_anything?
			'Fine. Be that way!'
		when @what_say.yelling?
			"Woah, chill out!"
		when @what_say.question?
			"Sure."
		else
		  "Whatever."
		end
	end
end 

class WhatSay

	def initialize(word)
		@word = word
	end
	
	def question?
		@word.end_with?("?")
	end

	def yelling?
		@word.upcase == @word
	end

	def without_anything?
		@word.strip.empty?
	end
end
