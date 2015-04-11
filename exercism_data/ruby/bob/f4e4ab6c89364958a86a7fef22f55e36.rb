class Bob
	def hey(str)
	clause = Sentence.new(str)
	return 'Fine. Be that way!' if clause.nothing?
	return 'Woah, chill out!' if clause.yelling?
	return 'Sure.' if clause.question?
	return clause.default
	end

	class Sentence
		attr_accessor :sentence
		def initialize(str)
      		@sentence = str
    	end

		def nothing?
			@sentence.to_s.strip.empty?
		end

		def yelling?
			@sentence == @sentence.upcase
		end

		def question?
			@sentence.end_with? '?'
		end

		def default
			'Whatever.'
		end
	end
end
