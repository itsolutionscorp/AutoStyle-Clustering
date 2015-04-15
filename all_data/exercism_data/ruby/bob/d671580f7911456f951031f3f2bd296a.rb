module Sentence
	def shout?(sentence)
		sentence.upcase == sentence
	end

	def question?(sentence)
		sentence.end_with?("?")
	end

	def silence?(sentence)
		sentence.strip.empty?
	end
end

class Bob
	include Sentence 
	
	def hey(str)
		if(silence?(str))
			"Fine. Be that way!"
		elsif(shout?(str))
			"Woah, chill out!"
		elsif(question?(str))
			"Sure."
		else
			"Whatever."
		end
	end
end
