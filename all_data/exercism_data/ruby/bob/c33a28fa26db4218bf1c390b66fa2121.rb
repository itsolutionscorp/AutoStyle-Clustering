#!/usr/bin/env ruby
#Bob exercism assignment

class Sentence
	attr_accessor :sentence
	def initialize(sentence)
		@sentence = sentence
	end

	def yelling?
		return sentence == (sentence.upcase and not sentence.empty?)
	end

	def asking?
		return sentence.end_with?("?")
	end

	def unspoken?
		return sentence.empty?
	end
end

class Bob
	def hey(sentence)
		speak = Sentence.new(sentence)
		case 
		when speak.yelling?
			"Woah, chill out!"
		when speak.asking?
			"Sure."
		when speak.unspoken?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end
end
