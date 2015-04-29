class Bob
	def hey(phrase)
		what_was_said = Phrase.new(phrase)

		if what_was_said.is_silent?
			'Fine. Be that way!'		
		elsif what_was_said.is_shout?
			'Woah, chill out!'		
		elsif what_was_said.is_question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end

class Phrase
	attr_reader :phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def is_silent?
		phrase.strip.empty?
	end

	def is_shout?
		phrase == phrase.upcase
	end

	def is_question?
		phrase.end_with?('?')
	end
end
