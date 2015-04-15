#!/usr/bin/env ruby
#Bob exercism assignment

class Sentence
	attr_accessor :sentence
	def initialize(sentence)
		@sentence = sentence
	end
	def yelling?
		if sentence == sentence.upcase and not unspoken?(sentence)
			return true
		else
			return false
		end
	end

	def asking?
		if sentence.end_with?("?")
			return true
		else
			return false
		end
	end

	def unspoken?
		if sentence.empty?
			return true
		else
			return false
		end
	end
end

class Bob
	def hey(sentence)
		speak = Sentence.new(sentence)
		case speak
		when speak.yelling?
			return "Woah, chill out!"
		when speak.asking?
			return "Sure."
		when speak.unspoken?
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
	end
end
