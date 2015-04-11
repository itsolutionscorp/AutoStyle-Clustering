class Bob
	def hey sentence
		answerer = Answerer.new sentence
		return 'Fine. Be that way!' if answerer.empty?
		return 'Woah, chill out!' if answerer.yelling?
		return 'Sure.' if answerer.question?
		return 'Whatever.'
	end	
private 
	class Answerer
		attr_reader :sentence

		def initialize sentence
			@sentence = sentence
		end	

		def yelling?
		 	sentence.upcase == sentence		
		end
		
		def question?
			sentence[-1] == '?'
		end 

		def empty?
			sentence.nil? || sentence.strip.empty?
		end	
	end
end	
