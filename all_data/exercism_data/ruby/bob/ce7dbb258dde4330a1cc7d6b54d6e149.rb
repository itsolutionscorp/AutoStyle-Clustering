class Sentence
	def initialize(str)
		@value = str
	end

	def shout?
		@value.upcase == @value
	end

	def question?
		@value.end_with?("?")
	end

	def silence?
		@value.strip.empty?
	end
end

class Bob
	
	def hey(str)
		sentence = Sentence.new(str)
		if sentence.silence?
			"Fine. Be that way!"
		elsif sentence.shout?
			"Woah, chill out!"
		elsif sentence.question?
			"Sure."
		else
			"Whatever."
		end
	end
end
